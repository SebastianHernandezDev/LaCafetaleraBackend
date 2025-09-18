package com.LaCafetalera.API_REST.DTO;

public class ProductoDTO{
    private String nombre;
    private Long idCategoria;  // Solo el ID, no la entidad completa
    private Long precioUnitario;
    private int stock;
    private String descripcion;
    private String imagen;

    // Constructor vacío
    public ProductoDTO() {}

    // Constructor completo
    public ProductoDTO(String nombre, Long idCategoria, Long precioUnitario,
                       int stock, String descripcion, String imagen) {
        this.nombre = nombre;
        this.idCategoria = idCategoria;
        this.precioUnitario = precioUnitario;
        this.stock = stock;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Long getIdCategoria() { return idCategoria; }
    public void setIdCategoria(Long idCategoria) { this.idCategoria = idCategoria; }

    public Long getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Long precioUnitario) { this.precioUnitario = precioUnitario; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }
}