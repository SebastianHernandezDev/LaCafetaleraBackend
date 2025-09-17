package com.LaCafetalera.API_REST.services;

import com.LaCafetalera.API_REST.models.DetallePedido;
import com.LaCafetalera.API_REST.repositories.IDetallePedidoRepository;
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

    @Transactional(readOnly = true)
    public List<DetallePedido> getAll() {
        return detallePedidoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<DetallePedido> getById(Long id) {
        return detallePedidoRepository.findById(id);
    }

    public DetallePedido save(DetallePedido detallePedido) {
        // Calcula subtotal automáticamente
        if (detallePedido.getProducto() != null) {
            detallePedido.setSubtotal(detallePedido.getProducto().getPrecioUnitario() * detallePedido.getCantidad());
        }
        return detallePedidoRepository.save(detallePedido);
    }

    public DetallePedido update(Long id, DetallePedido detallePedido) {
        return detallePedidoRepository.findById(id)
                .map(existingDetalle -> {
                    existingDetalle.setProducto(detallePedido.getProducto());
                    existingDetalle.setCantidad(detallePedido.getCantidad());

                    // Recalcular subtotal
                    if (existingDetalle.getProducto() != null) {
                        existingDetalle.setSubtotal(existingDetalle.getProducto().getPrecioUnitario() * existingDetalle.getCantidad());
                    } else {
                        existingDetalle.setSubtotal(0L);
                    }

                    return detallePedidoRepository.save(existingDetalle);
                })
                .orElseThrow(() -> new RuntimeException("DetallePedido no encontrado con id: " + id));
    }

    public void deleteById(Long id) {
        if (!detallePedidoRepository.existsById(id)) {
            throw new RuntimeException("DetallePedido no encontrado con id: " + id);
        }
        detallePedidoRepository.deleteById(id);
    }
}

