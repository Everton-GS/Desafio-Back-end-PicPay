package com.picpayTeste.Backend.entity;

import org.hibernate.validator.constraints.br.CPF;
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
@Table(name = "db_usuario")
public class Usuario extends DadosUsuario {
    

    @CPF
    @NotBlank
    @Column(name = "cpf")
    private String cpf;
}
