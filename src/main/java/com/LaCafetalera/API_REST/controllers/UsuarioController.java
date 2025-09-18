package com.LaCafetalera.API_REST.controllers;

import com.LaCafetalera.API_REST.DTO.UsuarioDTO;
import com.LaCafetalera.API_REST.models.Usuario;
import com.LaCafetalera.API_REST.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;


    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        List<Usuario> usuarios = usuarioService.getAll();
        return ResponseEntity.ok(usuarios);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id) {
        return usuarioService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody UsuarioDTO usuarioDTO) {
        // Convertir DTO a entidad
        Usuario usuario = new Usuario(
                usuarioDTO.getNombre(),
                usuarioDTO.getApellido(),
                usuarioDTO.getTelefono(),
                usuarioDTO.getEmail(),
                usuarioDTO.getContrasena()
        );
        Usuario nuevoUsuario = usuarioService.save(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        try {
            // Buscar el usuario existente
            Usuario usuarioExistente = usuarioService.getById(id)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            // Actualizar campos desde el DTO
            usuarioExistente.setNombre(usuarioDTO.getNombre());
            usuarioExistente.setApellido(usuarioDTO.getApellido());
            usuarioExistente.setTelefono(usuarioDTO.getTelefono());
            usuarioExistente.setEmail(usuarioDTO.getEmail());
            usuarioExistente.setContrasena(usuarioDTO.getContrasena());

            Usuario actualizado = usuarioService.save(usuarioExistente);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            usuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
