package com.LaCafetalera.API_REST.controllers;

import com.LaCafetalera.API_REST.models.DetallePedido;
import com.LaCafetalera.API_REST.services.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalle-pedidos")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;


    @GetMapping
    public ResponseEntity<List<DetallePedido>> getAll() {
        return ResponseEntity.ok(detallePedidoService.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<DetallePedido> getById(@PathVariable Long id) {
        return detallePedidoService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<DetallePedido> create(@RequestBody DetallePedido detallePedido) {
        DetallePedido nuevoDetalle = detallePedidoService.save(detallePedido);
        return ResponseEntity.ok(nuevoDetalle);
    }


    @PutMapping("/{id}")
    public ResponseEntity<DetallePedido> update(@PathVariable Long id, @RequestBody DetallePedido detallePedido) {
        try {
            DetallePedido actualizado = detallePedidoService.update(id, detallePedido);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            detallePedidoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
