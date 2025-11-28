package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import lombok.*;



@Entity
@Table(name="usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario {

    @Id
    @Column(nullable = false, length=10)
    private String run;

    @Column(nullable = false, length=40)
    private String nombre;

    @Column(nullable = false, length=40)
    private String apellidos;

    @Column(nullable = false, length=40)
    private String correo;

    @JsonIgnore
    @Column(nullable = false, length=40)
    private String password;

    @Column(nullable = false, length=40)
    private String direccion;
    
    @Column(nullable = false, length=40)
    private String region;

    @Column(nullable = false, length=40)
    private String comuna;

    @Column(nullable = false)
    private LocalDate fechaNac;

    @Column(length=40)
    private String codigo;

    
}
