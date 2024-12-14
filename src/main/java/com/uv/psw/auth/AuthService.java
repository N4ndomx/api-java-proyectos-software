package com.uv.psw.auth;

import com.uv.psw.email.EmailService;
import com.uv.psw.empleados.Empleado;
import com.uv.psw.empleados.EmpleadoRepository;
import com.uv.psw.empleados.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoService empleadoService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final EmailService emailService;
    private final SecureRandom random = new SecureRandom();
    private static final String ALPHANUMERIC_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    @Autowired
    public AuthService(
            EmpleadoRepository empleadoRepository,
            EmpleadoService empleadoService,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil,
            EmailService emailService) {
        this.empleadoRepository = empleadoRepository;
        this.empleadoService = empleadoService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.emailService = emailService;
    }

    /**
     * Registrar un nuevo empleado con contraseña encriptada y código de verificación.
     * Este método es transaccional, por lo que si ocurre un error, no se guardará el empleado.
     *
     * @param empleado Datos del empleado a registrar.
     * @return Empleado registrado.
     * @throws RuntimeException si ocurre algún error durante el registro.
     */
    @Transactional
    public Empleado register(Empleado empleado) {
        try {
            // Encriptar la contraseña del empleado
            empleado.setPassword(passwordEncoder.encode(empleado.getPassword()));

            // Generar código de verificación
            String codigoVerificacion = generarCodigoVerificacion();
            empleado.setCodigoVerificacion(codigoVerificacion);
            empleado.setVerificado(false);

            // Enviar correo de verificación
            emailService.enviarCorreoVerificacion(
                    empleado.getCorreoE(),
                    empleado.getNombre(),
                    codigoVerificacion
            );

            // Usar EmpleadoService para registrar el empleado
            return empleadoService.crearEmpleado(empleado);
        } catch (Exception e) {
            throw new RuntimeException("Error durante el registro del empleado: " + e.getMessage(), e);
        }
    }

    /**
     * Iniciar sesión con credenciales (correo y contraseña).
     *
     * @param correo   Correo del usuario.
     * @param password Contraseña del usuario.
     * @return Token JWT si las credenciales son válidas.
     * @throws RuntimeException si ocurre algún error durante el inicio de sesión.
     */
    public String login(String correo, String password) {
        try {
            Empleado empleado = empleadoRepository.findByCorreoE(correo)
                    .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

            if (!empleado.isVerificado()) {
                throw new RuntimeException("El correo no ha sido verificado.");
            }

            if (!passwordEncoder.matches(password, empleado.getPassword())) {
                throw new RuntimeException("Credenciales incorrectas.");
            }

            return jwtUtil.generateToken(empleado.getCorreoE());
        } catch (Exception e) {
            throw new RuntimeException("Error durante el inicio de sesión: " + e.getMessage(), e);
        }
    }

    /**
     * Verificar un empleado mediante su correo y el código de verificación.
     *
     * @param correoE           Dirección de correo del empleado.
     * @param codigoVerificacion Código de verificación proporcionado.
     * @return Mensaje indicando si la verificación fue exitosa o no.
     * @throws RuntimeException si ocurre algún error durante la verificación.
     */
    @Transactional
    public String verificarEmpleado(String correoE, String codigoVerificacion) {
        try {
            Optional<Empleado> empleadoOpt = empleadoRepository.findByCorreoE(correoE);
            if (empleadoOpt.isEmpty()) {
                return "Empleado no encontrado.";
            }

            Empleado empleado = empleadoOpt.get();
            if (!codigoVerificacion.equals(empleado.getCodigoVerificacion())) {
                return "Código de verificación incorrecto.";
            }

            empleado.setVerificado(true);
            empleado.setCodigoVerificacion(null);
            empleadoRepository.save(empleado);

            return "Empleado verificado exitosamente.";
        } catch (Exception e) {
            throw new RuntimeException("Error al verificar el empleado: " + e.getMessage(), e);
        }
    }

    /**
     * Generar un código de verificación alfanumérico de 6 caracteres.
     *
     * @return Código generado.
     */
    private String generarCodigoVerificacion() {
        try {
            StringBuilder codigo = new StringBuilder(6);
            for (int i = 0; i < 6; i++) {
                int index = random.nextInt(ALPHANUMERIC_CHARS.length());
                codigo.append(ALPHANUMERIC_CHARS.charAt(index));
            }
            return codigo.toString().toUpperCase();
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el código de verificación: " + e.getMessage(), e);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Empleado> empleadoOpt = empleadoRepository.findByCorreoE(username);

        if (empleadoOpt.isEmpty()) {
            throw new UsernameNotFoundException("Empleado no encontrado con correo: " + username);
        }

        Empleado empleado = empleadoOpt.get();

        return User.builder()
                .username(empleado.getCorreoE())
                .password(empleado.getPassword())
                .roles(empleado.getRol().name())
                .build();
    }
}
