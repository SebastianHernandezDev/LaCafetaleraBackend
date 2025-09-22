package com.LaCafetalera.API_REST.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;
    private String nombre;

    /// relacion 1:N categoria y producto

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Producto> productoList = new ArrayList<>();

    /// constructor
    public Categoria(){

    }

    public Categoria(String nombre){
        this.nombre=nombre;
    }


    /// getters y setters
    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombre;
    }

    public void setNombreCategoria(String nombre) {
        this.nombre = nombre;
    }
}
