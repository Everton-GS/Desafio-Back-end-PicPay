package com.picpayTeste.Backend.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.picpayTeste.Backend.entity.Lojista;
import com.picpayTeste.Backend.entity.Usuario;
import com.picpayTeste.Backend.repository.LojistaRepository;
import com.picpayTeste.Backend.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    LojistaRepository lojistaRepository;

    public void registrar(Usuario usuario){
         String senhaBcrypte= new BCryptPasswordEncoder().encode(usuario.getSenha());
         usuario.setSenha(senhaBcrypte);
         usuarioRepository.save(usuario);
    }

    public void transferenciaUsuario(Usuario remetente,Usuario destinatario,BigDecimal valor){
            if (remetente.getCarteira().compareTo(valor)>0) {
                BigDecimal remetenteValorAtualizado=remetente.getCarteira().subtract(valor);
                remetente.setCarteira(remetenteValorAtualizado);

                BigDecimal destinatarioValorAtualizado=destinatario.getCarteira().add(valor);
                destinatario.setCarteira(destinatarioValorAtualizado);

                usuarioRepository.save(remetente);
                usuarioRepository.save(destinatario);
            }
    }

    public void transferenciaLojista(Usuario remetente, Lojista destinatario,BigDecimal valor){
            if(remetente.getCarteira().compareTo(valor)>0){
                BigDecimal remetenteValorAtualizdo=remetente.getCarteira().subtract(valor);
                remetente.setCarteira(remetenteValorAtualizdo);
                usuarioRepository.save(remetente);

                BigDecimal destinatarioValorAtualizado=destinatario.getCarteira().add(valor);
                destinatario.setCarteira(destinatarioValorAtualizado);
                lojistaRepository.save(destinatario);
                

            }
    }
}
