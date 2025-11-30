package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.backend.model.Usuario;
import com.example.backend.service.UsuarioService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/usuario")

public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public Usuario create(@RequestBody Usuario usuario){
        usuario.setRol("USER");
        return usuarioService.crearUsuario(usuario);
    }

    @GetMapping("/{run}")
    public Usuario getByRun(@PathVariable String run) {
        return usuarioService.getByRun(run);
    }

    @GetMapping
    public List<Usuario> list(){
        return usuarioService.listarTodos();
    }

    @PutMapping("/{run}")
    public Usuario update(@PathVariable String run, @RequestBody Usuario usuario) {        
        return usuarioService.update(run, usuario);
    }

    @DeleteMapping("/{run}")
    public void delete(@PathVariable String run){
        usuarioService.delete(run);
    }

    
}
