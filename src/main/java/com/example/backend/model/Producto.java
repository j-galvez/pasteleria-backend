package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// @Entity: Marca esta clase como una entidad de JPA (tabla en la BD).
@Entity
public class Producto {

    // @Id y @GeneratedValue: Configuran 'id' como llave primaria auto-generada.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre_producto; // Ej: "Tarta de Chocolate", "Mousse de Fresa"
    private double precio;
    private String descripcion;

    // Este campo diferencia Postres de Tortas.
    private String categoria; // Valores posibles: "Torta", "Postre"

    // Constructor vacío (necesario para JPA)
    public Producto() {}

    // Constructor con campos (opcional, pero útil)
    public Producto(String nombre_producto, double precio, String descripcion, String categoria) {
        this.nombre_producto = nombre_producto;
        this.precio = precio;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    // --- Getters y Setters ---
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre_producto; }
    public void setNombre(String nombre) { this.nombre_producto = nombre; }
    
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}
    

