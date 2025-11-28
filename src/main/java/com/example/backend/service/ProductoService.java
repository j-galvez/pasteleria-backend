package com.example.backend.service;

import com.example.backend.model.Producto;
import com.example.backend.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Obtiene todos los productos
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    // Obtiene un producto por su ID
    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }
    
    // Guarda o actualiza un producto (El ID debe ser provisto por el cliente para la creación)
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    // DELETE - Eliminar un producto
    public void eliminarProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado");
        }
        productoRepository.deleteById(id);
    }
    
    // Método clave para filtrar por categoría (usado por los componentes JSX)
    public List<Producto> findByCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }
}