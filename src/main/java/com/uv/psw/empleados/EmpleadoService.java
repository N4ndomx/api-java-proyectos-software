package com.uv.psw.empleados;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    /**
     * Obtener un empleado por su correo electrónico.
     *
     * @param correo Correo del empleado.
     * @return Optional con el empleado encontrado o vacío si no existe.
     * @throws RuntimeException si ocurre algún error durante la búsqueda.
     */
    public Optional<Empleado> obtenerEmpleadoPorCorreo(String correo) {
        try {
            return empleadoRepository.findByCorreoE(correo);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el empleado por correo: " + e.getMessage(), e);
        }
    }

    /**
     * Obtener un empleado por su ID.
     *
     * @param id ID del empleado.
     * @return Optional con el empleado encontrado o vacío si no existe.
     * @throws RuntimeException si ocurre algún error durante la búsqueda.
     */
    public Optional<Empleado> obtenerEmpleadoPorId(String id) {
        try {
            return empleadoRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el empleado por ID: " + e.getMessage(), e);
        }
    }

    /**
     * Listar todos los empleados registrados.
     *
     * @return Iterable con todos los empleados registrados.
     * @throws RuntimeException si ocurre algún error durante la operación.
     */
    public Iterable<Empleado> listarEmpleados() {
        try {
            return empleadoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los empleados: " + e.getMessage(), e);
        }
    }

    /**
     * Crear un nuevo empleado.
     * Este método es transaccional para asegurar consistencia en la base de datos.
     *
     * @param empleado Datos del empleado a registrar.
     * @return El empleado registrado.
     * @throws RuntimeException si ocurre algún error durante la creación.
     */
    @Transactional
    public Empleado crearEmpleado(Empleado empleado) {
        try {
            empleado.setMatricula(generarClave(empleado.getRol()));
            
           
            return empleadoRepository.save(empleado);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el empleado: " + e.getMessage(), e);
        }
    }

    /**
     * Actualizar los datos de un empleado existente.
     * Este método es transaccional para asegurar consistencia en la base de datos.
     *
     * @param id               ID del empleado a actualizar.
     * @param empleadoDetalles Nuevos detalles del empleado.
     * @return Optional con el empleado actualizado o vacío si no se encontró.
     * @throws RuntimeException si ocurre algún error durante la actualización.
     */
    @Transactional
    public Optional<Empleado> actualizarEmpleado(String id, Empleado empleadoDetalles) {
        try {
            return empleadoRepository.findById(id).map(empleado -> {
                empleado.setNombre(empleadoDetalles.getNombre());
                empleado.setApellido(empleadoDetalles.getApellido());
                empleado.setSalario(empleadoDetalles.getSalario());
                empleado.setDireccion(empleadoDetalles.getDireccion());
                empleado.setTelefono(empleadoDetalles.getTelefono());
                empleado.setCorreoE(empleadoDetalles.getCorreoE());
                empleado.setRol(empleadoDetalles.getRol());
                return empleadoRepository.save(empleado);
            });
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el empleado: " + e.getMessage(), e);
        }
    }

    /**
     * Eliminar un empleado por su ID.
     * Este método es transaccional para asegurar consistencia en la base de datos.
     *
     * @param id ID del empleado a eliminar.
     * @throws RuntimeException si ocurre algún error durante la eliminación.
     */
    @Transactional
    public void eliminarEmpleado(String id) {
        try {
            empleadoRepository.findById(id).map(empleado -> {
                
                empleado.setEstado(false);
                return empleadoRepository.save(empleado);
            });
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el empleado: " + e.getMessage(), e);
        }
    }

    /**
     * Generar una clave única para un empleado basada en su rol.
     *
     * @param rol Rol del empleado.
     * @return Clave generada única para el empleado.
     */
    private String generarClave(Rol rol) {
        try {
            long count = empleadoRepository.countByRol(rol);
            return "E" + rol.name().charAt(0) + (count + 1);
        } catch (Exception e) {
            throw new RuntimeException("Error al generar la clave del empleado: " + e.getMessage(), e);
        }
    }
}
