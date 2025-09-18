package com.LaCafetalera.API_REST.DTO;

public class UsuarioDTO {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String contrasena;

    // Constructores
    public UsuarioDTO() {}

    public UsuarioDTO(String nombre, String apellido, String telefono, String email, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.contrasena = contrasena;
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
}