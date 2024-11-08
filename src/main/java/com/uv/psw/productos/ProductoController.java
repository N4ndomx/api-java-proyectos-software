/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/RestController.java to edit this template
 */
package com.uv.psw.productos;

import com.uv.psw.piezas.Pieza;
import com.uv.psw.piezas.PiezasRepository;
import com.uv.psw.productos.dto.ProductoCreateDTO;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
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
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping("api/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private PiezasRepository piezaRepository;

    @GetMapping("")
    public ResponseEntity<List<Producto>> list() {
        try {
            List<Producto> productos = productoRepository.findAll();
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            // Registra el error
            System.err.println("Error retrieving products: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener un producto por clave
    @GetMapping("/{clave}")
    public ResponseEntity<Producto> getProductoByClave(@PathVariable String clave) {
        Optional<Producto> producto = productoRepository.findById(Integer.parseInt(clave));
        return producto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody ProductoCreateDTO productoDTO) {
        if (productoRepository.existsById(productoDTO.getClave())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Ya existe un producto con la clave: " + productoDTO.getClave()
            );
        }
        Producto producto = new Producto();
        producto.setClave(productoDTO.getClave());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setCantidad(productoDTO.getCantidad());
        producto.setEstado(true);

        // Convertir los IDs de piezas a objetos Pieza y agregarlos al producto
        Set<Pieza> piezas = productoDTO.getPiezasIds().stream()
                .map(id -> piezaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pieza no encontrada: " + id)))
                .collect(Collectors.toSet());

        producto.setPiezas(piezas);

        Producto nuevoProducto = productoRepository.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    // Actualizar un producto existente
    @PutMapping("/{clave}")
    public ResponseEntity<Producto> updateProducto(@PathVariable String clave, @RequestBody ProductoCreateDTO productoDTO) {

        Producto producto = productoRepository.findById(Integer.parseInt(clave)).get();
        producto.setClave(Integer.parseInt(clave));
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setCantidad(productoDTO.getCantidad());

        // Convertir los IDs de piezas a objetos Pieza y agregarlos al producto
        Set<Pieza> piezas = productoDTO.getPiezasIds().stream()
                .map(id -> piezaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pieza no encontrada: " + id)))
                .collect(Collectors.toSet());

        producto.setPiezas(piezas);

        Producto nuevoProducto = productoRepository.save(producto);
        return ResponseEntity.status(HttpStatus.OK).body(nuevoProducto);
    }

    // Eliminar un producto
    @DeleteMapping("/{clave}")
    public ResponseEntity<Object> deleteProducto(@PathVariable String clave) {
        Producto producto = productoRepository.findById(Integer.parseInt(clave)).get();
        producto.setEstado(false);
        Producto nuevoProducto = productoRepository.save(producto);

        return ResponseEntity.status(HttpStatus.OK).body(nuevoProducto);
    }
}
