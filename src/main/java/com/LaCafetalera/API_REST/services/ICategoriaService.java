package com.LaCafetalera.API_REST.services;

import com.LaCafetalera.API_REST.models.Categoria;

import java.util.List;
import java.util.Optional;

public interface ICategoriaService {
    List<Categoria> getAll();
    Optional<Categoria> getById(Long id);
    Categoria save(Categoria categoria);
    Categoria update(Long id, Categoria categoria);
    void deleteById(Long id);
}
