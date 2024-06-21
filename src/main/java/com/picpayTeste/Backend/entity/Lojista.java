package com.picpayTeste.Backend.entity;

import org.hibernate.validator.constraints.br.CNPJ;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "db_lojista")
public class Lojista extends DadosUsuario{
    
    @CNPJ
    @NotBlank
    @Column(name = "cnpj")
    private String cnpj;

}
