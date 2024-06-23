package com.picpayTeste.Backend.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
        lojistaService.registraLojista(lojista);
        return ResponseEntity.ok().build();
    }

}
