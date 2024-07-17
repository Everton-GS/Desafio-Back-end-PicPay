package com.picpayTeste.Backend.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.picpayTeste.Backend.DTO.AcessoAplicacaoDTO;
import com.picpayTeste.Backend.entity.Lojista;
import com.picpayTeste.Backend.entity.Usuario;
import com.picpayTeste.Backend.infra.TokenService;
import com.picpayTeste.Backend.repository.LojistaRepository;
import com.picpayTeste.Backend.repository.UsuarioRepository;

@RestController
@RequestMapping(path = "/picpay")
public class AcessoAplicacao {

    @Autowired
    TokenService tokenService;

    @Autowired
    LojistaRepository lojistaRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UsuarioRepository usuarioRepository;

    @SuppressWarnings("null")
    @PostMapping("/acessar")
    public ResponseEntity<?> acessarAplicacao(@RequestBody AcessoAplicacaoDTO acessoAplicacaoDTO){
        Authentication auth;
        String token;
        UserDetails userDetails;

        if(acessoAplicacaoDTO.getLogin().length()==18){
          userDetails = lojistaRepository.findByCnpj(acessoAplicacaoDTO.getLogin());
          if(userDetails!=null && passwordEncoder.matches(acessoAplicacaoDTO.getSenha(),userDetails.getPassword())){
            auth = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
            token=tokenService.gerarTokenLojista((Lojista)auth.getPrincipal());
            return ResponseEntity.ok(token);

          }
        }
        if(acessoAplicacaoDTO.getLogin().length()==14){
          userDetails = usuarioRepository.findByCpf(acessoAplicacaoDTO.getLogin());
          if(userDetails!=null && passwordEncoder.matches(acessoAplicacaoDTO.getSenha(), userDetails.getPassword()));
          auth = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(auth);
          token=tokenService.gerarTokenUsuario((Usuario)auth.getPrincipal());
          return ResponseEntity.ok(token);
        }
          return ResponseEntity.badRequest().build();
    }
  }
    

