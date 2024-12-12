/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/RestController.java to edit this template
 */
package com.uv.psw.empleados;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uv.psw.empleados.dto.EmpleadoCreateDTO;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @PostMapping
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody EmpleadoCreateDTO empleadoDTO) {
        Empleado nuevoEmpleado = new Empleado();
        nuevoEmpleado.setMatricula(empleadoDTO.getMatricula());
        nuevoEmpleado.setNombre(empleadoDTO.getNombre());
        nuevoEmpleado.setApellido(empleadoDTO.getApellido());
        nuevoEmpleado.setSalario(empleadoDTO.getSalario());
        nuevoEmpleado.setSeguroSocial(empleadoDTO.getSeguroSocial());
        nuevoEmpleado.setDireccion(empleadoDTO.getDireccion());
        nuevoEmpleado.setTelefono(empleadoDTO.getTelefono());
        nuevoEmpleado.setCorreo(empleadoDTO.getCorreo());
        nuevoEmpleado.setFechaN(empleadoDTO.getFechaN());
        nuevoEmpleado.setClavePuesto(empleadoDTO.getClavePuesto());
        nuevoEmpleado.setClaveSupervisor(empleadoDTO.getClaveSupervisor());
        nuevoEmpleado.setAñoContratacion(empleadoDTO.getAñoContratacion());
        nuevoEmpleado.setEstado(true); // Estado activo al crear

        Empleado empleadoGuardado = empleadoRepository.save(nuevoEmpleado);
        return ResponseEntity.ok(empleadoGuardado);
    }

    @GetMapping("")
    public ResponseEntity<List<Empleado>> list() {
        try {
            List<Empleado> empleados = empleadoRepository.findAll();
            return ResponseEntity.ok(empleados);
        } catch (Exception e) {
            // Registra el error
            System.err.println("Error retrieving products: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener un empleado por su clave
    @GetMapping("/{matricula}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorClave(@PathVariable String matricula) {
        Optional<Empleado> empleado = empleadoRepository.findById(matricula);
        return empleado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // "Eliminar" un empleado (cambiar el estado a inactivo)
    @DeleteMapping("/{matricula}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable String matricula) {
        Optional<Empleado> empleadOptional = empleadoRepository.findById(matricula);
        if (empleadOptional.isPresent()) {
            Empleado empleado = empleadOptional.get();
            empleado.setEstado(false);
            empleadoRepository.save(empleado);
            return ResponseEntity.ok("Empleado desactivado con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable String matricula, @RequestBody EmpleadoCreateDTO empleadoDTO) {

        Empleado actualizarEmpleado = empleadoRepository.findById((matricula)).get();
        actualizarEmpleado.setMatricula((matricula));
        actualizarEmpleado.setNombre(empleadoDTO.getNombre());
        actualizarEmpleado.setApellido(empleadoDTO.getApellido());
        actualizarEmpleado.setSalario(empleadoDTO.getSalario());
        actualizarEmpleado.setSeguroSocial(empleadoDTO.getSeguroSocial());
        actualizarEmpleado.setDireccion(empleadoDTO.getDireccion());
        actualizarEmpleado.setTelefono(empleadoDTO.getTelefono());
        actualizarEmpleado.setCorreo(empleadoDTO.getCorreo());
        actualizarEmpleado.setFechaN(empleadoDTO.getFechaN());
        actualizarEmpleado.setClavePuesto(empleadoDTO.getClavePuesto());
        actualizarEmpleado.setClaveSupervisor(empleadoDTO.getClaveSupervisor());

        Empleado nuevoEmpleado = empleadoRepository.save(actualizarEmpleado);
        return ResponseEntity.status(HttpStatus.OK).body(nuevoEmpleado);
    }
}
