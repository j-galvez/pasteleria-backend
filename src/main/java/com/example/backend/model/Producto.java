package com.example.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    // @Id 
    @Id
    @Column(nullable = false)
    private Long id;
    
    @Column(nullable = false,length=100)
    private String nombre_producto; 

    @Column(nullable = false)
    private double precio;


    @Column(nullable = false,length=500)
    private String descripcion;

    @Column(nullable = false,length=50)
    private String categoria; 

    
}