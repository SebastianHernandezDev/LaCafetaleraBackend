package com.LaCafetalera.API_REST.repositories;

import com.LaCafetalera.API_REST.models.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDetallePedidoRepository extends JpaRepository<DetallePedido,Long> {
    List<DetallePedido> findByPedidoId(Long pedidoId);

}
