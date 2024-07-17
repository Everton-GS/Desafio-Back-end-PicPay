package com.picpayTeste.Backend.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.picpayTeste.Backend.entity.Lojista;
import com.picpayTeste.Backend.repository.LojistaRepository;


@Service
public class LojistaService {

    @Autowired
    LojistaRepository lojistaRepository;

    public void registraLojista(Lojista lojista){
        String senhaBcrypte= new BCryptPasswordEncoder().encode(lojista.getSenha());
        lojista.setSenha(senhaBcrypte);
        lojistaRepository.save(lojista);
    }
}
