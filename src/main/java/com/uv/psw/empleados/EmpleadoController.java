
package com.uv.psw.empleados;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping
    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtenerEmpleado(@PathVariable Long id) {
        return empleadoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody Empleado empleado) {
        Empleado nuevoEmpleado = empleadoRepository.save(empleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEmpleado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado empleadoDetalles) {
        return empleadoRepository.findById(id)
                .map(empleado -> {
                    empleado.setClave(empleadoDetalles.getClave());
                    empleado.setNombre(empleadoDetalles.getNombre());
                    empleado.setApellido(empleadoDetalles.getApellido());
                    empleado.setSalario(empleadoDetalles.getSalario());
                    empleado.setDireccion(empleadoDetalles.getDireccion());
                    empleado.setTelefono(empleadoDetalles.getTelefono());
                    empleado.setCorreoE(empleadoDetalles.getCorreoE());
                    Empleado actualizado = empleadoRepository.save(empleado);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long id) {
        if (empleadoRepository.existsById(id)) {
            empleadoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
