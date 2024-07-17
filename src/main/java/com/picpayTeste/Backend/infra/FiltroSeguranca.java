package com.picpayTeste.Backend.infra;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.picpayTeste.Backend.repository.LojistaRepository;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class FiltroSeguranca extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired LojistaRepository lojistaRepository;
    
    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain)throws ServletException, IOException{
        String token= RequestTratamentoToken(request);
        if(token!=null){
            String cnpj=tokenService.validarToken(token);
            UserDetails lojista= lojistaRepository.findByCnpj(cnpj);

            if(lojista!=null){
               var authentication= new UsernamePasswordAuthenticationToken(lojista,null,lojista.getAuthorities());
               SecurityContextHolder.getContext().setAuthentication(authentication);
            }       
        }
        filterChain.doFilter(request, response);
    }


    private String RequestTratamentoToken(HttpServletRequest request){
        String header=request.getHeader("Authorization");
        if(header!=null){
            return header.replace("Bearer", "");
        }
        return null;
    }
}
