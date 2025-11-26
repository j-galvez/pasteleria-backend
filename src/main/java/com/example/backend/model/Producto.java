package com.example.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Entity: Marca esta clase como una entidad de JPA (tabla en la BD).
@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    // @Id y @GeneratedValue: Configuran 'id' como llave primaria auto-generada.
    @Id
    @Column(nullable = false)
    private Long id;
    
    @Column (nullable = false, length=100)
    private String nombre_producto; // Ej: "Tarta de Chocolate", "Mousse de Fresa"

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false, length = 255)
    private String descripcion;

    // Este campo diferencia Postres de Tortas.
    @Column(nullable = false, length = 50)
    private String categoria; // Valores posibles: "Torta", "Postre"
}
