package com.LaCafetalera.API_REST.controllers;

import com.LaCafetalera.API_REST.DTO.CategoriaDTO;
import com.LaCafetalera.API_REST.models.Categoria;
import com.LaCafetalera.API_REST.services.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private ICategoriaService categoriaService;


    @GetMapping
    public ResponseEntity<List<Categoria>> getAll() {
        List<Categoria> categorias = categoriaService.getAll();
        return ResponseEntity.ok(categorias);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable Long id) {
        return categoriaService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Categoria> save(@RequestBody CategoriaDTO categoriaDTO) {
        // Convertir DTO a entidad
        Categoria categoria = new Categoria(categoriaDTO.getNombre());
        Categoria nuevaCategoria = categoriaService.save(categoria);
        return ResponseEntity.ok(nuevaCategoria);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO) {
        try {
            // Buscar categoría existente
            Categoria categoriaExistente = categoriaService.getById(id)
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

            // Actualizar desde DTO
            categoriaExistente.setNombreCategoria(categoriaDTO.getNombre());

            Categoria actualizada = categoriaService.save(categoriaExistente);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            categoriaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
