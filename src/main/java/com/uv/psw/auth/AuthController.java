package com.uv.psw.auth;

import com.uv.psw.auth.dto.LoginRequest;
import com.uv.psw.auth.dto.LoginResponse;
import com.uv.psw.empleados.Empleado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Registrar un nuevo empleado.
     *
     * @param empleado Datos del empleado a registrar.
     * @return Empleado registrado.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Empleado empleado) {
        try {
            Empleado registrado = authService.register(empleado);
            return ResponseEntity.status(HttpStatus.CREATED).body(registrado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Iniciar sesión con correo y contraseña.
     *
     * @param loginRequest Contiene el correo y la contraseña del usuario.
     * @return Token JWT si las credenciales son válidas.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = authService.login(loginRequest.getCorreo(), loginRequest.getPassword());
            return ResponseEntity.ok().body(new LoginResponse(token));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    /**
     * Verificar un empleado mediante su correo y el código de verificación.
     *
     * @param correo             Dirección de correo del empleado.
     * @param codigoVerificacion Código de verificación.
     * @return Mensaje indicando si la verificación fue exitosa.
     */
    @PostMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam String correo, @RequestParam String codigoVerificacion) {
        try {
            String resultado = authService.verificarEmpleado(correo, codigoVerificacion);
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
