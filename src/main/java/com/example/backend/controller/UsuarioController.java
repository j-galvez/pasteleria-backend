package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.service.UsuarioService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



import com.example.backend.model.Usuario;
@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "http://localhost:3000")

public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public Usuario create(@RequestBody Usuario usuario){
        return usuarioService.crearUsuario(usuario);
    }    
}
