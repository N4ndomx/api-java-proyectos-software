package com.uv.psw.manufactura;

import com.uv.psw.manufactura.dto.ProcesoManufacturaCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/procesos-manufactura")
public class ProcesoManufacturaController {

    @Autowired
    private ProcesoManufacturaReadRepository readRepository;

    @Autowired
    private ProcesoManufacturaWriteRepository writeRepository;

    // Obtener todos usando VARCHAR
    @GetMapping("")
    public ResponseEntity<List<ProcesoManufacturaRead>> listarProcesosManufactura() {
        try {
            List<ProcesoManufacturaRead> procesos = readRepository.findAll();
            return ResponseEntity.ok(procesos);
        } catch (Exception e) {
            System.err.println("Error retrieving manufacturing processes: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener por ID usando VARCHAR
    @GetMapping("/{clave}")
    public ResponseEntity<ProcesoManufacturaRead> obtenerProcesoPorClave(@PathVariable Integer clave) {
        Optional<ProcesoManufacturaRead> proceso = readRepository.findById(clave);
        return proceso.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear usando BigDecimal
    @PostMapping
    public ResponseEntity<ProcesoManufacturaWrite> crearProcesoManufactura(@RequestBody ProcesoManufacturaCreateDTO procesoDTO) {
        try {
            ProcesoManufacturaWrite nuevoProceso = new ProcesoManufacturaWrite();
            nuevoProceso.setClaveMaterial(procesoDTO.getClaveMaterial());
            nuevoProceso.setClavePieza(procesoDTO.getClavePieza());
            nuevoProceso.setCostoFabricacion(procesoDTO.getCostoFabricacion());
            nuevoProceso.setTiempo(procesoDTO.getTiempo());
            nuevoProceso.setNumeroProduccion(procesoDTO.getNumeroProduccion());
            nuevoProceso.setUnidadMedida(procesoDTO.getUnidadMedida());

            ProcesoManufacturaWrite procesoGuardado = writeRepository.save(nuevoProceso);
            return ResponseEntity.ok(procesoGuardado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    // Actualizar usando BigDecimal
    @PutMapping("/{clave}")
    public ResponseEntity<ProcesoManufacturaWrite> actualizarProcesoManufactura(
            @PathVariable Integer clave,
            @RequestBody ProcesoManufacturaCreateDTO procesoDTO) {
        
        Optional<ProcesoManufacturaWrite> procesoOptional = writeRepository.findById(clave);
        
        if (procesoOptional.isPresent()) {
            ProcesoManufacturaWrite proceso = procesoOptional.get();
            proceso.setClaveMaterial(procesoDTO.getClaveMaterial());
            proceso.setClavePieza(procesoDTO.getClavePieza());
            proceso.setCostoFabricacion(procesoDTO.getCostoFabricacion());
            proceso.setTiempo(procesoDTO.getTiempo());
            proceso.setNumeroProduccion(procesoDTO.getNumeroProduccion());
            proceso.setUnidadMedida(procesoDTO.getUnidadMedida());

            ProcesoManufacturaWrite procesoActualizado = writeRepository.save(proceso);
            return ResponseEntity.ok(procesoActualizado);
        }
        
        return ResponseEntity.notFound().build();
    }

    // Eliminar usando VARCHAR
    @DeleteMapping("/{clave}")
    public ResponseEntity<Void> eliminarProcesoManufactura(@PathVariable Integer clave) {
        if (readRepository.existsById(clave)) {
            readRepository.deleteById(clave);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}