package com.picpayTeste.Backend.entity;

import java.util.Collection;
import java.util.Collections;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Table(name = "db_lojista", uniqueConstraints = @UniqueConstraint(columnNames = {"cnpj"}))
public class Lojista extends DadosUsuario implements UserDetails{
    
    @CNPJ
    @NotBlank
    @Column(name = "cnpj",unique = true)
    private String cnpj;

    @Column(name = "tipo_usuario")
    @Enumerated
    private TipoUsuarioEnum tipoEnum;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(tipoEnum.getTipo()));
    }

    @Override
    public String getPassword() {
        return getPassword();
    }

    @Override
    public String getUsername() {
       return getCnpj();
    }


    
}
