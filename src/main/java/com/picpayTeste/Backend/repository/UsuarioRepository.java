package com.picpayTeste.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.picpayTeste.Backend.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    

    @Query(value = "Select * from db_usuario where cpf=:cpf",nativeQuery = true)
    Usuario findByCpf(@Param("cpf")String cpf);
}
