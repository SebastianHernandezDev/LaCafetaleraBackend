package com.LaCafetalera.API_REST.services;

import com.LaCafetalera.API_REST.models.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    List<Producto> getAll();
    Optional<Producto> getById(Long id);
    Producto save(Producto producto);
    Producto update(Long id, Producto producto);
    void deleteById(Long id);
}
