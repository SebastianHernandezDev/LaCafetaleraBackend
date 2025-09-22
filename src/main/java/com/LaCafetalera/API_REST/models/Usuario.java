package com.LaCafetalera.API_REST.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;  // mejor usar snake_case para BD

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String telefono;

    @Column(unique = true, nullable = false)
    @Email
    @NotBlank(message = "el correo no debe de estar vacio")
    private String email;

    @Column(nullable = false)
    private String contrasena;
/////////////////////////////////////////////////////////
    @Column(nullable = false)
    private boolean habilitado = true;



    /// relacion
    @OneToMany(mappedBy = "usuario", cascade =CascadeType.ALL ,orphanRemoval = true)
    @JsonManagedReference
    private List<Pedido> pedidoList = new ArrayList<>();


    /// Constructor
    public Usuario() {}



    public Usuario(String nombre, String apellido, String telefono, String email, String contrasena){
        this.nombre=nombre;
        this.apellido=apellido;
        this.telefono=telefono;
        this.email=email;
        this.contrasena=contrasena;
    }



    /// getters y setters
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    ///   ///////////////////////////////////////////////////////

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
}
