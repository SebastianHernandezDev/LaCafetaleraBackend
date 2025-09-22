package com.LaCafetalera.API_REST.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    @JsonBackReference
    private Usuario usuario;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPedido;

    @Column(nullable = false)
    private Long total = 0L;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
   @JsonManagedReference
    private List<DetallePedido> detallePedidoList = new ArrayList<>();

    // Constructores
    public Pedido() {}

    public Pedido(Usuario usuario) {
        this.usuario = usuario;
        this.fechaPedido = new Date();
    }

    @PrePersist
    protected void onCreate() {
        if (this.fechaPedido == null) {
            this.fechaPedido = new Date();
        }
    }

    // Getters y setters
    public Long getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Date getFechaPedido() { return fechaPedido; }
    public void setFechaPedido(Date fechaPedido) { this.fechaPedido = fechaPedido; }
    public Long getTotal() { return total; }
    public void setTotal(Long total) { this.total = total; }
    public List<DetallePedido> getDetallePedidoList() { return detallePedidoList; }
    public void setDetallePedidoList(List<DetallePedido> detallePedidoList) { this.detallePedidoList = detallePedidoList; }
}

