package com.LaCafetalera.API_REST.config;

import com.LaCafetalera.API_REST.models.Usuario;
import com.LaCafetalera.API_REST.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private IUsuarioRepository usuarioRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Solo crear si no existe
        if (usuarioRepo.findByEmail("admin@lacafetalera.com").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNombre("Admin");
            admin.setApellido("Principal");
            admin.setEmail("admin@lacafetalera.com");
            admin.setContrasena(passwordEncoder.encode("Admin123*"));
            admin.setTelefono("0000000000");
            admin.setHabilitado(true);
            usuarioRepo.save(admin);
        }
    }
}
