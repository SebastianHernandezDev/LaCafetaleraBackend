package com.LaCafetalera.API_REST.services;

import com.LaCafetalera.API_REST.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    List<Usuario> getAll();
    Optional<Usuario> getById(Long id);
    Usuario save(Usuario usuario);
    Usuario update(Long id, Usuario usuario);
    void deleteById(Long id);
    Usuario findByEmail(String email);
}