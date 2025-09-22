package com.LaCafetalera.API_REST.services;

import com.LaCafetalera.API_REST.models.DetallePedido;
import com.LaCafetalera.API_REST.models.Pedido;
import com.LaCafetalera.API_REST.models.Producto;
import com.LaCafetalera.API_REST.repositories.IDetallePedidoRepository;
import com.LaCafetalera.API_REST.repositories.IPedidoRepository;
import com.LaCafetalera.API_REST.repositories.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DetallePedidoService {

    @Autowired
    private IDetallePedidoRepository detallePedidoRepository;

    @Autowired
    private IProductoRepository productoRepository; // 👈 nuevo

    @Transactional(readOnly = true)
    public List<DetallePedido> getAll() {
        return detallePedidoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<DetallePedido> getById(Long id) {
        return detallePedidoRepository.findById(id);
    }

    @Autowired
    private IPedidoRepository pedidoRepository;

    public DetallePedido save(DetallePedido detallePedido) {
        // 1. Buscar el pedido en BD
        var pedidoBD = pedidoRepository.findById(detallePedido.getPedido().getId())
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        // 2. Buscar el producto en BD
        var productoBD = productoRepository.findById(detallePedido.getProducto().getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // 3. Reemplazar los objetos incompletos por los de BD
        detallePedido.setPedido(pedidoBD);
        detallePedido.setProducto(productoBD);

        // 4. Calcular subtotal
        detallePedido.setSubtotal(productoBD.getPrecioUnitario() * detallePedido.getCantidad());

        // 5. Guardar el detalle
        DetallePedido detalleGuardado = detallePedidoRepository.save(detallePedido);

        // 6. Actualizar el total del pedido sumando TODOS los detalles desde BD
        Long nuevoTotal = detallePedidoRepository.findByPedidoId(pedidoBD.getId())
                .stream()
                .mapToLong(DetallePedido::getSubtotal)
                .sum();

        // 7. Actualizar total en BD
        pedidoBD.setTotal(nuevoTotal);
        pedidoRepository.save(pedidoBD);

        return detalleGuardado;
    }



    public DetallePedido update(Long id, DetallePedido detallePedido) {
        return detallePedidoRepository.findById(id)
                .map(existingDetalle -> {
                    // Traer producto real desde BD
                    if (detallePedido.getProducto() != null && detallePedido.getProducto().getId() != null) {
                        Producto productoReal = productoRepository.findById(detallePedido.getProducto().getId())
                                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
                        existingDetalle.setProducto(productoReal);
                        existingDetalle.setSubtotal(productoReal.getPrecioUnitario() * detallePedido.getCantidad());
                    }

                    // Actualizar cantidad
                    existingDetalle.setCantidad(detallePedido.getCantidad());

                    // Guardar detalle actualizado
                    DetallePedido actualizado = detallePedidoRepository.save(existingDetalle);

                    // Recalcular total del pedido
                    Pedido pedido = actualizado.getPedido();
                    Long total = detallePedidoRepository.findByPedidoId(pedido.getId()).stream()
                            .mapToLong(DetallePedido::getSubtotal)
                            .sum();
                    pedido.setTotal(total);
                    pedidoRepository.save(pedido);

                    return actualizado;
                })
                .orElseThrow(() -> new RuntimeException("DetallePedido no encontrado con id: " + id));
    }

    public void deleteById(Long id) {
        DetallePedido detalle = detallePedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DetallePedido no encontrado con id: " + id));

        Pedido pedido = detalle.getPedido();

        // Borrar detalle
        detallePedidoRepository.deleteById(id);

        // Recalcular total después de eliminar
        Long total = detallePedidoRepository.findByPedidoId(pedido.getId()).stream()
                .mapToLong(DetallePedido::getSubtotal)
                .sum();
        pedido.setTotal(total);
        pedidoRepository.save(pedido);
    }
}
