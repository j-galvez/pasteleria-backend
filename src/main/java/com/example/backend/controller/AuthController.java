package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.backend.model.Usuario;
import com.example.backend.service.UsuarioService;
import com.example.backend.model.LoginRequest;
import com.example.backend.model.LoginResponse;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        // 1. Buscar usuario por correo
        Usuario usuario = usuarioService.getByCorreo(request.getCorreo());

        if (usuario == null) {
            throw new RuntimeException("Correo no encontrado");
        }

        // 2. Comparar contraseña
        if (!usuario.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // 3. Simular token temporal (cuando agreguemos JWT, se reemplaza)
        String tokenTemporal = "TOKEN_" + usuario.getRun();

        // 4. Retornar LoginResponse CON TODOS LOS DATOS del usuario
        return new LoginResponse(
            tokenTemporal,
            usuario.getRol(),
            usuario.getNombre(),
            usuario.getRun(),       
            usuario.getApellidos(), 
            usuario.getCorreo(),    
            usuario.getDireccion(), 
            usuario.getRegion(),   
            usuario.getComuna()
        );
    }
}