package com.LaCafetalera.API_REST.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/info")
    public Map<String, String> getAdmin(Authentication authentication) {
        String email = authentication.getName();
        if (!"admin@lacafetalera.com".equals(email)) {
            throw new RuntimeException("No autorizado");
        }
        return Map.of(
                "correo", email,
                "rol", "admin"
        );
    }
}
