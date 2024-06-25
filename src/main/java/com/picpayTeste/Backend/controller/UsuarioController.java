package com.picpayTeste.Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.picpayTeste.Backend.entity.Usuario;
import com.picpayTeste.Backend.service.UsuarioService;

@RestController
@RequestMapping(path = "/usuario")
public class UsuarioController {
    

    @Autowired
    UsuarioService usuarioService;

    
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario){
            try {
                usuarioService.registrar(usuario);
                return ResponseEntity.ok().build();
            } catch (Exception e) {
               return ResponseEntity.internalServerError().build();
            }  

    }
}
