package com.LaCafetalera.API_REST.services;

import com.LaCafetalera.API_REST.models.DetallePedido;
import com.LaCafetalera.API_REST.models.Pedido;
import com.LaCafetalera.API_REST.repositories.IPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PedidoService implements IPedidoService {

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Transactional(readOnly = true)
    public List<Pedido> getAll() {
        return pedidoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Pedido> getById(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido save(Pedido pedido) {
        actualizarTotal(pedido);
        return pedidoRepository.save(pedido);
    }

    public Pedido update(Long id, Pedido pedido) {
        return pedidoRepository.findById(id)
                .map(existingPedido -> {
                    existingPedido.setUsuario(pedido.getUsuario());
                    existingPedido.setFechaPedido(pedido.getFechaPedido());
                    existingPedido.setDetallePedidoList(pedido.getDetallePedidoList());

                    // Actualiza el total automáticamente
                    actualizarTotal(existingPedido);

                    return pedidoRepository.save(existingPedido);
                })
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
    }

    public void deleteById(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido no encontrado con id: " + id);
        }
        pedidoRepository.deleteById(id);
    }


    private void actualizarTotal(Pedido pedido) {
        long total = 0L;
        if (pedido.getDetallePedidoList() != null) {
            for (DetallePedido detalle : pedido.getDetallePedidoList()) {
                if (detalle.getProducto() != null) {
                    detalle.setSubtotal(detalle.getProducto().getPrecioUnitario() * detalle.getCantidad());
                    total += detalle.getSubtotal();
                }
            }
        }
        pedido.setTotal(total);
    }
}
