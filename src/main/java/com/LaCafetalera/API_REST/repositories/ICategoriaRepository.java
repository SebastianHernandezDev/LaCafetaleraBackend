package com.LaCafetalera.API_REST.repositories;

import com.LaCafetalera.API_REST.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaRepository extends JpaRepository<Categoria,Long> {
}
