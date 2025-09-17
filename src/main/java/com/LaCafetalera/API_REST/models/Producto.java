package com.LaCafetalera.API_REST.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProducto", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "idCategoria")
//    @JsonBackReference
    private Categoria categoria;


    @Column(nullable = false)
    private Long precioUnitario;

    @Column(nullable = false)
    private int stock;

    @Lob
    private String descripcion;

    private String imagen;



    @OneToMany(mappedBy = "producto",cascade = CascadeType.ALL)
//    @JsonManagedReference
    @JsonIgnore
    private List<DetallePedido> detallePedidoList = new ArrayList<>();



    /// constructores
    public Producto(){

    }

    public Producto(String nombre, Long precioUnitario, int stock, String descripcion, String imagen,Categoria categoria) {
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.stock = stock;
        this.descripcion=descripcion;
        this.imagen=imagen;
        this.categoria=categoria;
    }
    /// setter y getters

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Long precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    public Categoria getCategoria(){
        return categoria;
    }
    public void setCategoria(Categoria categoria){
        this.categoria = categoria;
    }

}
