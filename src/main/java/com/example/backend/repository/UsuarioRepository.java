package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    
}
