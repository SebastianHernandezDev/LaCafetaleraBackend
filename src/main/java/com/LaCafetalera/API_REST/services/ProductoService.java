package com.LaCafetalera.API_REST.services;

import com.LaCafetalera.API_REST.models.Producto;
import com.LaCafetalera.API_REST.repositories.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> getById(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto update(Long id, Producto producto) {
        return productoRepository.findById(id)
                .map(existingProducto -> {
                    existingProducto.setNombre(producto.getNombre());
                    existingProducto.setPrecioUnitario(producto.getPrecioUnitario());
                    existingProducto.setStock(producto.getStock());
                    existingProducto.setDescripcion(producto.getDescripcion());
                    existingProducto.setImagen(producto.getImagen());

                    // Solo actualizar categoría si no es null
                    if (producto.getCategoria() != null) {
                        existingProducto.setCategoria(producto.getCategoria());
                    }

                    return productoRepository.save(existingProducto);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
        productoRepository.deleteById(id);
    }
}