package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String rol;
    private String nombre;
    private String run;      
    private String apellidos;
    private String correo;   
    private String direccion;
    private String region;
    private String comuna;    
}