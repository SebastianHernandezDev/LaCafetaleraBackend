package com.LaCafetalera.API_REST.controllers;

import com.LaCafetalera.API_REST.DTO.DetallePedidoDTO;
import com.LaCafetalera.API_REST.models.DetallePedido;
import com.LaCafetalera.API_REST.models.Pedido;
import com.LaCafetalera.API_REST.models.Producto;
import com.LaCafetalera.API_REST.services.DetallePedidoService;
import com.LaCafetalera.API_REST.services.IPedidoService;
import com.LaCafetalera.API_REST.services.IProductoService;
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


    @Autowired
    private IPedidoService pedidoService; // Agregar esta inyección
    @Autowired
    private IProductoService productoService; // Agregar esta inyección

    @PostMapping
    public ResponseEntity<DetallePedido> create(@RequestBody DetallePedidoDTO detallePedidoDTO) {
        // Buscar pedido
        Pedido pedido = pedidoService.getById(detallePedidoDTO.getIdPedido())
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        // Buscar producto
        Producto producto = productoService.getById(detallePedidoDTO.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Crear detalle
        DetallePedido detallePedido = new DetallePedido(pedido, producto, detallePedidoDTO.getCantidad());
        DetallePedido nuevoDetalle = detallePedidoService.save(detallePedido);
        return ResponseEntity.ok(nuevoDetalle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallePedido> update(@PathVariable Long id, @RequestBody DetallePedidoDTO detallePedidoDTO) {
        try {
            // Buscar detalle existente
            DetallePedido detalleExistente = detallePedidoService.getById(id)
                    .orElseThrow(() -> new RuntimeException("DetallePedido no encontrado"));

            // Buscar pedido y producto
            Pedido pedido = pedidoService.getById(detallePedidoDTO.getIdPedido())
                    .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
            Producto producto = productoService.getById(detallePedidoDTO.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            // Actualizar campos
            detalleExistente.setPedido(pedido);
            detalleExistente.setProducto(producto);
            detalleExistente.setCantidad(detallePedidoDTO.getCantidad());

            DetallePedido actualizado = detallePedidoService.save(detalleExistente);
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
