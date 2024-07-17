package com.picpayTeste.Backend.entity;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class DadosUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    @NotBlank(message="nome em branco")
    @Column(name = "nome")
    private String nome;

    @NotBlank(message = "e-mail em branco")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "senha em branco")
    @Column(name = "senha")
    private String senha;

    @NotNull(message = "carteira em branco")
    
    @PositiveOrZero
    @Column(name = "carteira")
    private BigDecimal carteira;
}
