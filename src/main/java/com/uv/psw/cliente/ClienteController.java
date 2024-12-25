package com.uv.psw.cliente;

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

import com.uv.psw.cliente.dto.ClienteCreateDto;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    //Obtener todos los clientes
    @GetMapping("")
    public ResponseEntity<List<Cliente>> list() {
        try {
            List<Cliente> clientes = clienteRepository.findAll();
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            // Registra el error
            System.err.println("Error retrieving customers: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener una cliente por su clave
    @GetMapping("/{clave}")
    public ResponseEntity<Cliente> obtenerClientePorClave(@PathVariable Integer clave) {
        Optional<Cliente> cliente = clienteRepository.findById(clave);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // "Eliminar" un cliente (cambiar el estado a inactivo)
    @DeleteMapping("/{clave}")
    public ResponseEntity<String> eliminarCliente(@PathVariable Integer clave) {
        Optional<Cliente> cienteOptional = clienteRepository.findById(clave);
        if (cienteOptional.isPresent()) {
            Cliente cliente = cienteOptional.get();
            cliente.setEstado(false); // Marcamos a cliente como inactivo
            clienteRepository.save(cliente);
            return ResponseEntity.ok("Cliente desactivado con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un cliente nuevo
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody ClienteCreateDto clienteDto) {
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setClave(clienteDto.getClave());
        nuevoCliente.setNombre(clienteDto.getNombre());
        nuevoCliente.setRFC(clienteDto.getRFC());
        nuevoCliente.setDireccion(clienteDto.getDireccion());
        nuevoCliente.setEstado(true); // Estado activo al crear el cliente
        Cliente clienteGuardado = clienteRepository.save(nuevoCliente);
        return ResponseEntity.ok(clienteGuardado);
    }

    @PutMapping("/{clave}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable String clave, @RequestBody ClienteCreateDto clienteDto) {
        Cliente clienteUpdate = clienteRepository.findById(Integer.valueOf(clave)).get();
        clienteUpdate.setNombre(clienteDto.getNombre());
        clienteUpdate.setRFC(clienteDto.getRFC());
        clienteUpdate.setDireccion(clienteDto.getDireccion());
        Cliente clienteActualizado = clienteRepository.save(clienteUpdate);
        return ResponseEntity.status(HttpStatus.OK).body(clienteActualizado);
    }
}
