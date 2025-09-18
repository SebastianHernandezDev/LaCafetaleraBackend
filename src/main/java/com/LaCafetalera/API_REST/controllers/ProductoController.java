package com.LaCafetalera.API_REST.controllers;

import com.LaCafetalera.API_REST.DTO.ProductoDTO;
import com.LaCafetalera.API_REST.models.Categoria;
import com.LaCafetalera.API_REST.models.Producto;
import com.LaCafetalera.API_REST.services.ICategoriaService;
import com.LaCafetalera.API_REST.services.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;


    @GetMapping
    public ResponseEntity<List<Producto>> getAll() {
        List<Producto> productos = productoService.getAll();
        return ResponseEntity.ok(productos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Producto> getById(@PathVariable Long id) {
        return productoService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @Autowired
    private ICategoriaService categoriaService; // Necesitas inyectar esto también

    @PostMapping
    public ResponseEntity<Producto> save(@RequestBody ProductoDTO productoDTO) {
        // Buscar la categoría por ID
        Categoria categoria = categoriaService.getById(productoDTO.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        // Convertir DTO a entidad
        Producto producto = new Producto(
                productoDTO.getNombre(),
                productoDTO.getPrecioUnitario(),
                productoDTO.getStock(),
                productoDTO.getDescripcion(),
                productoDTO.getImagen(),
                categoria
        );

        Producto nuevoProducto = productoService.save(producto);
        return ResponseEntity.ok(nuevoProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        try {
            // Buscar producto existente
            Producto productoExistente = productoService.getById(id)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            // Buscar categoría
            Categoria categoria = categoriaService.getById(productoDTO.getIdCategoria())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

            // Actualizar campos
            productoExistente.setNombre(productoDTO.getNombre());
            productoExistente.setCategoria(categoria);
            productoExistente.setPrecioUnitario(productoDTO.getPrecioUnitario());
            productoExistente.setStock(productoDTO.getStock());
            productoExistente.setDescripcion(productoDTO.getDescripcion());
            productoExistente.setImagen(productoDTO.getImagen());

            Producto actualizado = productoService.save(productoExistente);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            productoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
