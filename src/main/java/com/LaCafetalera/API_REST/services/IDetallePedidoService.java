package com.LaCafetalera.API_REST.services;

import com.LaCafetalera.API_REST.models.DetallePedido;

import java.util.List;
import java.util.Optional;

public interface IDetallePedidoService {

    List<DetallePedido> getAll();
    Optional<DetallePedido> getById(Long id);
    DetallePedido save(DetallePedido detallePedido);
    DetallePedido update(Long id, DetallePedido detallePedido);
    void deleteById(Long id);
}

