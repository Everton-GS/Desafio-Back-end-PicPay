package com.picpayTeste.Backend.infra;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.picpayTeste.Backend.entity.Lojista;
import com.picpayTeste.Backend.entity.Usuario;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    String secret;
    
    public String gerarTokenLojista(Lojista lojista) {
            Algorithm algorithm = Algorithm.HMAC512(secret);
            return JWT.create().withIssuer("picpay").withSubject(lojista.getCnpj()).withExpiresAt(tempoToke())
                    .sign(algorithm);
    }
    public String gerarTokenUsuario(Usuario usuario) {
        Algorithm algorithm = Algorithm.HMAC512(secret);
        return JWT.create().withIssuer("picpay")
        .withSubject(usuario.getCpf())
        .withExpiresAt(tempoToke())
                .sign(algorithm);
}

    public String validarToken(String token) {
            Algorithm algorithm = Algorithm.HMAC512(secret);
            return JWT.require(algorithm)
                    .withIssuer("picpay")
                    .build()
                    .verify(token)
                    .getSubject();
    }

    public Instant tempoToke() {
        return LocalDateTime.now().plusMinutes(10).toInstant(ZoneOffset.of("-03:00"));
    }
}
