package com.example.backend.controller;

import com.example.backend.model.Producto;
import com.example.backend.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
// Permite que React (por defecto en puerto 3000) pueda acceder a esta API
@CrossOrigin(origins = "http://localhost:3000") 
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // GET: http://localhost:8080/api/productos
    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.findAll();
    }

    // GET: http://localhost:8080/api/productos/categoria/Torta
    @GetMapping("/categoria/{categoria}")
    public List<Producto> getProductosByCategoria(@PathVariable String categoria) {
        return productoService.findByCategoria(categoria);
    }
    
    // GET: http://localhost:8080/api/productos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        return productoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // POST: http://localhost:8080/api/productos
    @PostMapping
    // Devuelve 201 Created si el producto es nuevo
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        // NOTA: Se asume que el objeto 'producto' recibido ya trae un ID válido asignado.
        Producto nuevoProducto = productoService.save(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    // DELETE: http://localhost:8080/api/productos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        if (productoService.findById(id).isPresent()) {
            productoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
