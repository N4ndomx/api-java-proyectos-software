/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/RestController.java to edit this template
 */
package com.uv.psw.piezas;

import com.uv.psw.piezas.dto.PiezaCreateDTO;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping("/api/piezas")
public class PiezaController {

    @Autowired
    private PiezasRepository piezaRepository;

    // Crear una nueva pieza
    @PostMapping
    public ResponseEntity<Pieza> crearPieza(@RequestBody PiezaCreateDTO piezaDTO) {
        Pieza nuevaPieza = new Pieza();
        nuevaPieza.setClave(piezaDTO.getClave());
        nuevaPieza.setNombre(piezaDTO.getNombre());
        nuevaPieza.setDescripcion(piezaDTO.getDescripcion());
        nuevaPieza.setExistencia(piezaDTO.getExistencia());
        nuevaPieza.setCosto(piezaDTO.getCosto());
        nuevaPieza.setEstado(true); // Estado activo al crear
        Pieza piezaGuardada = piezaRepository.save(nuevaPieza);
        return ResponseEntity.ok(piezaGuardada);
    }

    @GetMapping("")
    public ResponseEntity<List<Pieza>> list() {
        try {
            List<Pieza> productos = piezaRepository.findAll();
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            // Registra el error
            System.err.println("Error retrieving products: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener una pieza por su clave
    @GetMapping("/{clave}")
    public ResponseEntity<Pieza> obtenerPiezaPorClave(@PathVariable Integer clave) {
        Optional<Pieza> pieza = piezaRepository.findById(clave);
        return pieza.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // "Eliminar" una pieza (cambiar el estado a inactivo)
    @DeleteMapping("/{clave}")
    public ResponseEntity<String> eliminarPieza(@PathVariable Integer clave) {
        Optional<Pieza> piezaOptional = piezaRepository.findById(clave);
        if (piezaOptional.isPresent()) {
            Pieza pieza = piezaOptional.get();
            pieza.setEstado(false); // Marcamos la pieza como inactiva
            piezaRepository.save(pieza);
            return ResponseEntity.ok("Pieza desactivada con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{clave}")
    public ResponseEntity<Pieza> updateProducto(@PathVariable String clave, @RequestBody PiezaCreateDTO piezaDTO) {

        Pieza pieza = piezaRepository.findById(Integer.valueOf(Integer.parseInt(clave))).get();
        pieza.setClave(Integer.parseInt(clave));
        pieza.setDescripcion(piezaDTO.getDescripcion());
        pieza.setNombre(piezaDTO.getNombre());
        pieza.setDescripcion(piezaDTO.getDescripcion());
        pieza.setExistencia(piezaDTO.getExistencia());
        pieza.setCosto(piezaDTO.getCosto());
//        pieza.setEstado(pieza.); // Estado activo al crear

        Pieza nuevoProducto = piezaRepository.save(pieza);
        return ResponseEntity.status(HttpStatus.OK).body(nuevoProducto);
    }
}
