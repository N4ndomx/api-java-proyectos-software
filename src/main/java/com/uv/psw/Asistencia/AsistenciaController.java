package com.uv.psw.Asistencia;


import com.uv.psw.Asistencia.dto.AsistenciaCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asistencias")
public class AsistenciaController {




    @Autowired
    private AsistenciaRepository asistenciaRepository;


    // Listar todas las asistencias
    @GetMapping("")
    public ResponseEntity<List<Asistencia>> listarAsistencias() {
        List<Asistencia> asistencias = asistenciaRepository.findAll();
        return ResponseEntity.ok(asistencias);
    }



    // Crear una nueva asistencia
    @PostMapping
    public ResponseEntity<Asistencia> crearAsistencia(@RequestBody AsistenciaCreateDTO asistenciaDTO) {
        Asistencia nuevaAsistencia = new Asistencia();
        nuevaAsistencia.setClaveEmpleado(asistenciaDTO.getClaveEmpleado());
        nuevaAsistencia.setFecha(asistenciaDTO.getFecha());
        nuevaAsistencia.setHora(asistenciaDTO.getHora());
        nuevaAsistencia.setTipoAsistencia(TipoAsistencia.valueOf(asistenciaDTO.getTipoAsistencia().toUpperCase()));

        Asistencia asistenciaGuardada = asistenciaRepository.save(nuevaAsistencia);

        return ResponseEntity.status(HttpStatus.CREATED).body(asistenciaGuardada);
    }


    // Obtener una asistencia por clave
    @GetMapping("/{clave}")
    public ResponseEntity<Asistencia> obtenerAsistenciaPorClave(@PathVariable Integer clave) {
        Optional<Asistencia> asistencia = asistenciaRepository.findById(clave);

        return asistencia.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // Eliminar una asistencia
    @DeleteMapping("/{clave}")
    public ResponseEntity<String> eliminarAsistencia(@PathVariable Integer clave) {
        Optional<Asistencia> asistenciaOptional = asistenciaRepository.findById(clave);

        if (asistenciaOptional.isPresent()) {
            asistenciaRepository.deleteById(clave);
            return ResponseEntity.ok("Asistencia eliminada con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Actualizar una asistencia existente
    @PutMapping("/{clave}")
    public ResponseEntity<Asistencia> actualizarAsistencia(@PathVariable Integer clave,
                                                           @RequestBody AsistenciaCreateDTO asistenciaDTO) {
        Optional<Asistencia> asistenciaOptional = asistenciaRepository.findById(clave);

        if (asistenciaOptional.isPresent()) {
            Asistencia asistencia = asistenciaOptional.get();
            asistencia.setClaveEmpleado(asistenciaDTO.getClaveEmpleado());
            asistencia.setFecha(asistenciaDTO.getFecha());
            asistencia.setHora(asistenciaDTO.getHora());
            asistencia.setTipoAsistencia(TipoAsistencia.valueOf(asistenciaDTO.getTipoAsistencia().toUpperCase()));

            Asistencia actualizada = asistenciaRepository.save(asistencia);
            return ResponseEntity.ok(actualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
