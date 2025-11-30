package com.example.backend.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String correo;
    private String password;
    
}
