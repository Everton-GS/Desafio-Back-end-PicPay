package com.picpayTeste.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpayTeste.Backend.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    
}
