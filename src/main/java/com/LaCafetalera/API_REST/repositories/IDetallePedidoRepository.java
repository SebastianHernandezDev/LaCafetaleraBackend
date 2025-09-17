package com.LaCafetalera.API_REST.repositories;

import com.LaCafetalera.API_REST.models.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDetallePedidoRepository extends JpaRepository<DetallePedido,Long> {
}
