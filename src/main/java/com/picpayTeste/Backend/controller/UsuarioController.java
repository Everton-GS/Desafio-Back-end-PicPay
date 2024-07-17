package com.picpayTeste.Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.picpayTeste.Backend.DTO.TransferenciaValor;
import com.picpayTeste.Backend.entity.Lojista;
import com.picpayTeste.Backend.entity.Usuario;
import com.picpayTeste.Backend.repository.LojistaRepository;
import com.picpayTeste.Backend.repository.UsuarioRepository;
import com.picpayTeste.Backend.service.UsuarioService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping(path = "/usuario")
public class UsuarioController {
    

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    LojistaRepository lojistaRepository;

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrar(@RequestBody @Validated Usuario usuario){
                usuarioService.registrar(usuario);
                return ResponseEntity.ok().build();
    }

    @Transactional(rollbackOn = Exception.class)
    @PostMapping(path = "/transferir")
    public ResponseEntity<String> transferenciaValor(@RequestBody TransferenciaValor transferenciaValor ){
        
        Usuario usuario = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(usuario.getTipoEnum());
        if(transferenciaValor.getDestinatario().length()==14){
            Usuario destinatario = usuarioRepository.findByCpf(transferenciaValor.getDestinatario());
            if(destinatario!=null){ 
                usuarioService.transferenciaUsuario(usuario, destinatario, transferenciaValor.getValor());
            }
        }else if(transferenciaValor.getDestinatario().length()==18){
            Lojista lojista= lojistaRepository.findByCnpj(transferenciaValor.getDestinatario());   
             usuarioService.transferenciaLojista(usuario, lojista, transferenciaValor.getValor());
        }else{
            return ResponseEntity.badRequest().body("Destinatário não encontrado");
        }
        
        return ResponseEntity.ok("Transação realizada com sucesso");
     }
}
