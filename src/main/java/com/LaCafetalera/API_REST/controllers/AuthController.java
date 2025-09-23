package com.LaCafetalera.API_REST.controllers;

import com.LaCafetalera.API_REST.DTO.AuthResponse;
import com.LaCafetalera.API_REST.DTO.UsuarioDTO;
import com.LaCafetalera.API_REST.models.Usuario;
import com.LaCafetalera.API_REST.services.IUsuarioService;
import com.LaCafetalera.API_REST.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Registro de usuario
    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody UsuarioDTO usuarioDTO) {
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

    // Login (admin hardcodeado + usuarios normales)
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UsuarioDTO usuarioDTO) {

        //  Comprobar admin hardcodeado
        if ("admin@lacafetalera.com".equals(usuarioDTO.getEmail()) &&
                "Admin123*".equals(usuarioDTO.getContrasena())) {

            // Creamos un UserDetails temporal para el admin
            UserDetails adminUser = User.builder()
                    .username(usuarioDTO.getEmail())
                    .password("")
                    .roles("Admin")
                    .build();

            String token = jwtService.generateToken(adminUser);

            return ResponseEntity.ok(new AuthResponse(token, "admin", usuarioDTO.getEmail()));
        }

        // Autenticar usuarios normales en DB
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        usuarioDTO.getEmail(),
                        usuarioDTO.getContrasena()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token, "usuario", usuarioDTO.getEmail()));
    }

    @GetMapping("/test")
    public String test() {
        return "Auth controller funcionando!";
    }
}
