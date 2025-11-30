package com.example.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.example.backend.model.Usuario;
import com.example.backend.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario crearUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario getByRun(String run){
        return usuarioRepository.findById(run).orElse(null);
    }

    public void delete(String run){
        usuarioRepository.deleteById(run);
    }

    public Usuario update(String run, Usuario usuario){
        Usuario existing = getByRun(run);
        if (existing == null) return null;
        return usuarioRepository.save(usuario);
    }
    

    public List<Usuario> listarTodos(){
        return usuarioRepository.findAll();
    }

    public Usuario getByCorreo(String correo) {
    return usuarioRepository.findByCorreo(correo);
}




}
