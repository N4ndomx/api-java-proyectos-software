package com.uv.psw.empleados;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    /**
     * Listar todos los empleados.
     *
     * @return Lista de empleados.
     */
    @GetMapping
    public ResponseEntity<Iterable<Empleado>> listarEmpleados() {
        try {
            Iterable<Empleado> empleados = empleadoService.listarEmpleados();
            return ResponseEntity.ok(empleados);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Obtener un empleado por su correo.
     *
     * @param correo Correo del empleado.
     * @return Empleado encontrado.
     */
    @GetMapping("/buscar")
    public ResponseEntity<Empleado> obtenerEmpleadoPorCorreo(@RequestParam String correo) {
        try {
            Optional<Empleado> empleado = empleadoService.obtenerEmpleadoPorCorreo(correo);
            return empleado.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Obtener un empleado por su ID.
     *
     * @param id ID del empleado.
     * @return Empleado encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable String id) {
        try {
            Optional<Empleado> empleado = empleadoService.obtenerEmpleadoPorId(id);
            return empleado.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Crear un nuevo empleado.
     *
     * @param empleado Datos del empleado.
     * @return Empleado creado.
     */
    @PostMapping
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody Empleado empleado) {
        try {
            Empleado nuevoEmpleado = empleadoService.crearEmpleado(empleado);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEmpleado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Actualizar los datos de un empleado.
     *
     * @param id       ID del empleado a actualizar.
     * @param empleado Nuevos datos del empleado.
     * @return Empleado actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable String id, @RequestBody Empleado empleado) {
        try {
            Optional<Empleado> actualizado = empleadoService.actualizarEmpleado(id, empleado);
            return actualizado.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Eliminar un empleado por su ID.
     *
     * @param id ID del empleado a eliminar.
     * @return Respuesta indicando éxito o error.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable String id) {
        try {
            empleadoService.eliminarEmpleado(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
