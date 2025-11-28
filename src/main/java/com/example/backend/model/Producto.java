package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// @Entity: Marca esta clase como una entidad de JPA (tabla en la BD).
@Entity
@Table(name = "producto")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Producto {

    // @Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
