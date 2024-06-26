package com.picpayTeste.Backend.infra;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.picpayTeste.Backend.entity.Lojista;
import com.picpayTeste.Backend.entity.Usuario;



@Service
public class TokenService {
    
    @Value("${api.security.secret}")
    String secret;

    @Autowired
    Lojista lojista;

    @Autowired
    Usuario usuario;
    
    public String gerarTokenLojista(Lojista lojista){

      try{
            Algorithm algorithm = Algorithm.HMAC512(secret);
            return JWT.create().withIssuer("picpay").
            withSubject(lojista.getCnpj()).
            withExpiresAt(tempoToke()).
            sign(algorithm);
          }catch(JWTCreationException e){
            return "Erro na geração do token"+e;
            }
        }

    public String validarToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC512(secret);

            return JWT.require(algorithm)
               .withIssuer("picpay")
               .build()
               .verify(token)
               .getSubject();
            
        } catch (JWTVerificationException e) {
            return "Erro na verificação"+e;
        }
    }

    public Instant tempoToke(){
        return LocalDateTime.now().plusMinutes(10).toInstant(ZoneOffset.of("-03:00"));
    }
}


