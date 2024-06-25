package com.picpayTeste.Backend.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpayTeste.Backend.entity.Usuario;
import com.picpayTeste.Backend.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    public void registrar(Usuario usuario){
         usuarioRepository.save(usuario);
    }

    public void transferencia(Usuario remetente,Usuario destinatario,BigDecimal valor){
            if (remetente.getCarteira().compareTo(valor)>0) {
                BigDecimal remetenteValorAtualizado=remetente.getCarteira().subtract(valor);
                remetente.setCarteira(remetenteValorAtualizado);

                BigDecimal destinatarioValorAtualizado=destinatario.getCarteira().add(valor);
                destinatario.setCarteira(destinatarioValorAtualizado);

                usuarioRepository.save(remetente);
                usuarioRepository.save(destinatario);
            }
    }
}
