package com.LaCafetalera.API_REST.services;

import com.LaCafetalera.API_REST.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    List<Usuario> getAll();
    Optional<Usuario> getById(Long id);
    Usuario save(Usuario usuario);
    Usuario update(Long id, Usuario usuario);
    void deleteById(Long id);
}
