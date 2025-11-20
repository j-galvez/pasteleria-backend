package com.example.backend.model;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name="usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @Column(nullable = false, length=10)
    private String rut;

    @Column(nullable = false, length=40)
    private String nombre;

    @Column(nullable = false, length=40)
    private String apellido;

    @Column(nullable = false, length=40)
    private String correo;

    @Column(nullable = false, length=40)
    private String contrasena;

    @Column(nullable = false, length=40)
    private String direccion;
    
    @Column(nullable = false, length=40)
    private String region;

    @Column(nullable = false, length=40)
    private String comuna;

    @Column(nullable = false, length=40)
    private String fecha_nac;

    @Column(length=40)
    private String cod_descuento;

    
}
