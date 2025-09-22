package com.LaCafetalera.API_REST.controllers;

import com.LaCafetalera.API_REST.DTO.PedidoDTO;
import com.LaCafetalera.API_REST.models.Pedido;
import com.LaCafetalera.API_REST.models.Usuario;
import com.LaCafetalera.API_REST.services.IUsuarioService;
import com.LaCafetalera.API_REST.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;


    @GetMapping
    public ResponseEntity<List<Pedido>> getAll() {
        return ResponseEntity.ok(pedidoService.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getById(@PathVariable Long id) {
        return pedidoService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @Autowired
    private IUsuarioService usuarioService; // Necesitas agregar esta inyección

    @PostMapping
    public ResponseEntity<Pedido> create(Authentication authentication) {
        // El email viene del token JWT
        String email = authentication.getName();

        // Buscar usuario por email
        Usuario usuario = usuarioService.findByEmail(email);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado con email: " + email);
        }

        // Crear el pedido vacío vinculado al usuario autenticado
        Pedido pedido = new Pedido(usuario);
        Pedido nuevoPedido = pedidoService.save(pedido);

        return ResponseEntity.ok(nuevoPedido);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Pedido> update(@PathVariable Long id, @RequestBody PedidoDTO pedidoDTO) {
        try {
            // Buscar pedido existente
            Pedido pedidoExistente = pedidoService.getById(id)
                    .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

            // Buscar nuevo usuario
            Usuario usuario = usuarioService.getById(pedidoDTO.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            // Actualizar usuario del pedido
            pedidoExistente.setUsuario(usuario);
            // La fecha y total se mantienen igual

            Pedido actualizado = pedidoService.save(pedidoExistente);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            pedidoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
