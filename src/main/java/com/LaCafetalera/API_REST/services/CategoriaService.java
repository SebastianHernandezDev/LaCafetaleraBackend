package com.LaCafetalera.API_REST.services;

import com.LaCafetalera.API_REST.models.Categoria;
import com.LaCafetalera.API_REST.repositories.ICategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoriaService implements ICategoriaService {

    @Autowired
    private ICategoriaRepository categoriaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> getAll() {
        return categoriaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Categoria> getById(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria update(Long id, Categoria categoria) {
        return categoriaRepository.findById(id)
                .map(existingCategoria -> {
                    existingCategoria.setNombreCategoria(categoria.getNombreCategoria());

                    return categoriaRepository.save(existingCategoria);
                })
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada con id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoria no encontrada con id: " + id);
        }
        categoriaRepository.deleteById(id);
    }
}