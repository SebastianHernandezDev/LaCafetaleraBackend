package com.LaCafetalera.API_REST.services;

import com.LaCafetalera.API_REST.models.Pedido;

import java.util.List;
import java.util.Optional;

public interface IPedidoService {

    List<Pedido> getAll();
    Optional<Pedido> getById(Long id);
    Pedido save(Pedido pedido);
    Pedido update(Long id, Pedido pedido);
    void deleteById(Long id);
}
