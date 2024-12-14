package com.uv.psw.Empleado;

import  com.uv.psw.Empleado.dto.EmpleadoCreateDto;
import com.uv.psw.Empleado.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {


    @Autowired
    private EmpleadoRepository empleadoRepository;


    // Crear un nuevo empleado
    @PostMapping
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody EmpleadoCreateDto empleadoDTO) {
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
        nuevoEmpleado.setAntiguedad(empleadoDTO.getAntiguedad());
        nuevoEmpleado.setEstado(true); // Estado activo al crear

        Empleado empleadoGuardado = empleadoRepository.save(nuevoEmpleado);
        return ResponseEntity.ok(empleadoGuardado);
    }

    // Listar todos los empleados
    @GetMapping("")
    public ResponseEntity<List<Empleado>> listarEmpleados() {
        List<Empleado> empleados = empleadoRepository.findAll();
        return ResponseEntity.ok(empleados);
    }

    // Obtener un empleado por matrícula
    @GetMapping("/{matricula}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorMatricula(@PathVariable String matricula) {
        Optional<Empleado> empleado = empleadoRepository.findById(matricula);

        return empleado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // "Eliminar" un empleado (cambiar el estado a inactivo)
    @DeleteMapping("/{matricula}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable String matricula) {
        Optional<Empleado> empleadoOptional = empleadoRepository.findById(matricula);

        if (empleadoOptional.isPresent()) {
            Empleado empleado = empleadoOptional.get();
            empleado.setEstado(false); // Marcamos al empleado como inactivo
            empleadoRepository.save(empleado);
            return ResponseEntity.ok("Empleado desactivado con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Actualizar un empleado existente
    @PutMapping("/{matricula}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable String matricula,
                                                       @RequestBody EmpleadoCreateDto empleadoDTO) {
        Optional<Empleado> empleadoOptional = empleadoRepository.findById(matricula);

        if (empleadoOptional.isPresent()) {
            Empleado empleado = empleadoOptional.get();
            empleado.setNombre(empleadoDTO.getNombre());
            empleado.setApellido(empleadoDTO.getApellido());
            empleado.setSalario(empleadoDTO.getSalario());
            empleado.setSeguroSocial(empleadoDTO.getSeguroSocial());
            empleado.setDireccion(empleadoDTO.getDireccion());
            empleado.setTelefono(empleadoDTO.getTelefono());
            empleado.setCorreo(empleadoDTO.getCorreo());
            empleado.setFechaN(empleadoDTO.getFechaN());
            empleado.setClavePuesto(empleadoDTO.getClavePuesto());
            empleado.setClaveSupervisor(empleadoDTO.getClaveSupervisor());
            empleado.setAñoContratacion(empleadoDTO.getAñoContratacion());
            empleado.setAntiguedad(empleadoDTO.getAntiguedad());

            Empleado actualizado = empleadoRepository.save(empleado);
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
