/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uv.psw.material;

import com.uv.psw.material.dto.MaterialCreateDTO;
import com.uv.psw.productos.Producto;
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
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Ville
 */
@RestController
@RequestMapping("api/material")
public class MaterialController {

    @Autowired
    private MaterialRepository materialRepository;

    
      @GetMapping("")
    public ResponseEntity<List<Material>> list() {
        try {
            List<Material> materiales = materialRepository.findAll();
            return ResponseEntity.ok(materiales);
        } catch (Exception e) {
            // Registra el error
            System.err.println("Error retrieving materials: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Obtener un producto por clave
    @GetMapping("/{clave}")
    public ResponseEntity<Material> getMaterialByClave(@PathVariable String clave) {
        Optional<Material> material = materialRepository.findById(Integer.parseInt(clave));
        return material.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
public ResponseEntity<Material> createMaterial(@RequestBody MaterialCreateDTO materialDTO) {
    // Validar si ya existe un material con la misma clave
    if (materialRepository.existsById(materialDTO.getClave())) {
        throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Ya existe un material con la clave: " + materialDTO.getClave()
        );
    }

    // Crear el objeto Material a partir del DTO
    Material material = new Material();
    material.setClave(materialDTO.getClave());
    material.setNombre(materialDTO.getNombre());
    material.setExistencia(materialDTO.getExistencia());
    material.setDescripcion(materialDTO.getDescripcion());
    material.setUnidadMedida(materialDTO.getUnidadMedida());
    material.setEstado(true); // Estado inicial como activo


    // Guardar el material en la base de datos
    Material nuevoMaterial = materialRepository.save(material);

    // Retornar la respuesta con el material creado
    return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMaterial);
}

    @PutMapping("/{clave}")
public ResponseEntity<Material> updateMaterial(@PathVariable String clave, @RequestBody MaterialCreateDTO materialDTO) {
    // Buscar el material existente por su clave
    Material material = materialRepository.findById(Integer.parseInt(clave))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Material no encontrado: " + clave));

    // Actualizar los campos del material con los valores del DTO
    material.setClave(materialDTO.getClave());
    material.setNombre(materialDTO.getNombre());
    material.setExistencia(materialDTO.getExistencia());
    material.setDescripcion(materialDTO.getDescripcion());
    material.setUnidadMedida(materialDTO.getUnidadMedida());

    // Guardar los cambios en el repositorio
    Material materialActualizado = materialRepository.save(material);

    // Retornar el material actualizado
    return ResponseEntity.status(HttpStatus.OK).body(materialActualizado);
}

    @DeleteMapping("/{clave}")
public ResponseEntity<Object> deleteMaterial(@PathVariable String clave) {
    // Buscar el material existente por su clave
    Material material = materialRepository.findById(Integer.parseInt(clave))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Material no encontrado: " + clave));

    // Desactivar el material estableciendo su estado a 'false'
    material.setEstado(false);

    // Guardar los cambios en el repositorio
    Material materialDesactivado = materialRepository.save(material);

    // Retornar el material desactivado como respuesta
    return ResponseEntity.status(HttpStatus.OK).body(materialDesactivado);
}

    
}
