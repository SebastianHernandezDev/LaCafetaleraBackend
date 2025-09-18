package com.LaCafetalera.API_REST.DTO;

public class DetallePedidoDTO {
    private Long idPedido;
    private Long idProducto;
    private Integer cantidad;

    // Constructor vacío
    public DetallePedidoDTO() {}

    // Constructor completo
    public DetallePedidoDTO(Long idPedido, Long idProducto, Integer cantidad) {
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    // Getters y setters
    public Long getIdPedido() { return idPedido; }
    public void setIdPedido(Long idPedido) { this.idPedido = idPedido; }

    public Long getIdProducto() { return idProducto; }
    public void setIdProducto(Long idProducto) { this.idProducto = idProducto; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}