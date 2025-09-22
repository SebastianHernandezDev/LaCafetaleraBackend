package com.LaCafetalera.API_REST.DTO;

public class AuthResponse {
    private String token;
    private String rol;
    private String correo;

    // Constructor vacío
    public AuthResponse() {}

    // Constructor con todos los campos
    public AuthResponse(String token, String rol, String correo) {
        this.token = token;
        this.rol = rol;
        this.correo = correo;
    }

    // Getters y setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
}
