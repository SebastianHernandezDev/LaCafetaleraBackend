package com.LaCafetalera.API_REST.repositories;

import com.LaCafetalera.API_REST.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPedidoRepository extends JpaRepository<Pedido,Long> {
}
