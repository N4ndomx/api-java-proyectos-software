package com.uv.psw.prestaciones;

import com.uv.psw.prestaciones.dto.PrestacionCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
 * @author Berna
 */

@RestController
@RequestMapping("/api/prestaciones")
public class PrestacionController {

    @Autowired
    private PrestacionesRepository prestacionesRepository;

    // Crear una nueva prestación
    @PostMapping
    public ResponseEntity<Prestacion> crearPrestacion(@RequestBody PrestacionCreateDTO prestacionDTO) {
        Prestacion nuevaPrestacion = new Prestacion();
        nuevaPrestacion.setClave(prestacionDTO.getClave());
        nuevaPrestacion.setPrestacion(prestacionDTO.getPrestacion());
        nuevaPrestacion.setValor(prestacionDTO.getValor());
        nuevaPrestacion.setTipoValor(prestacionDTO.getTipoValor());
        nuevaPrestacion.setEstado(true); // Estado activo al crear

        Prestacion prestacionGuardada = prestacionesRepository.save(nuevaPrestacion);
        return ResponseEntity.ok(prestacionGuardada);
    }

    // Listar todas las prestaciones
    @GetMapping("")
    public ResponseEntity<List<Prestacion>> listarPrestaciones() {
        try {
            List<Prestacion> prestaciones = prestacionesRepository.findAll();
            return ResponseEntity.ok(prestaciones);
        } catch (Exception e) {
            System.err.println("Error retrieving benefits: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener una prestación por su clave
    @GetMapping("/{clave}")
    public ResponseEntity<Prestacion> obtenerPrestacionPorClave(@PathVariable Integer clave) {
        Optional<Prestacion> prestacion = prestacionesRepository.findById(clave);
        return prestacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // "Eliminar" una prestación (cambiar el estado a inactivo)
    @DeleteMapping("/{clave}")
    public ResponseEntity<String> eliminarPrestacion(@PathVariable Integer clave) {
        Optional<Prestacion> prestacionOptional = prestacionesRepository.findById(clave);
        if (prestacionOptional.isPresent()) {
            Prestacion prestacion = prestacionOptional.get();
            prestacion.setEstado(false); // Marcamos la prestación como inactiva
            prestacionesRepository.save(prestacion);
            return ResponseEntity.ok("Prestación desactivada con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar una prestación
    @PutMapping("/{clave}")
    public ResponseEntity<Prestacion> actualizarPrestacion(@PathVariable String clave, @RequestBody PrestacionCreateDTO prestacionDTO) {
        Prestacion prestacion = prestacionesRepository.findById(Integer.parseInt(clave)).get();
        prestacion.setClave(Integer.parseInt(clave));
        prestacion.setPrestacion(prestacionDTO.getPrestacion());
        prestacion.setValor(prestacionDTO.getValor());
        prestacion.setTipoValor(prestacionDTO.getTipoValor());

        Prestacion nuevaPrestacion = prestacionesRepository.save(prestacion);
        return ResponseEntity.status(HttpStatus.OK).body(nuevaPrestacion);
    }
}