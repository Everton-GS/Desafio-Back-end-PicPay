package com.picpayTeste.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.picpayTeste.Backend.entity.Lojista;

public interface LojistaRepository extends JpaRepository<Lojista,Long>{
    
    @Query(value = "Select * from db_lojista where cnpj=:cnpj",nativeQuery = true)
    Lojista findByCnpj(@Param("cnpj")String cnpj);
}
