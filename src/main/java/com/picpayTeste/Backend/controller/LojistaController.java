package com.picpayTeste.Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.picpayTeste.Backend.entity.Lojista;
import com.picpayTeste.Backend.service.LojistaService;


@RestController
@RequestMapping(value = "/lojista")
public class LojistaController {

    @Autowired
    LojistaService lojistaService;
    

    @PostMapping("/registrar")
    public ResponseEntity<Lojista> registrar(@RequestBody @Validated Lojista lojista) {
        try {
            lojistaService.registraLojista(lojista);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
             return ResponseEntity.internalServerError().build();
        }
       
    }


    

}
