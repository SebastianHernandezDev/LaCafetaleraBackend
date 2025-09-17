package com.LaCafetalera.API_REST.repositories;

import com.LaCafetalera.API_REST.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository  extends JpaRepository<Producto,Long> {
}
