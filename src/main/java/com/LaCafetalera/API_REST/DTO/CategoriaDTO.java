package com.LaCafetalera.API_REST.DTO;

public class CategoriaDTO {
    private String nombre;

    // Constructor vacío
    public CategoriaDTO() {}

    // Constructor con parámetros
    public CategoriaDTO(String nombre) {
        this.nombre = nombre;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}