package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.backend.model.Usuario;
import com.example.backend.service.UsuarioService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    // Crear un admin (solo accesible para admins si usas JWT)
    @PostMapping("/crear-admin")
    public Usuario crearAdmin(@RequestBody Usuario usuario) {
        usuario.setRol("ADMIN");
        return usuarioService.crearUsuario(usuario);
    }
}
