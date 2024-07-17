package com.picpayTeste.Backend.exception;

import java.sql.SQLException;
import java.util.concurrent.TimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@RestControllerAdvice
public class ExceptionValidator {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(ex.getBindingResult().getAllErrors().toString());
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleSQLExceptions(SQLException ex) {
        return ResponseEntity.badRequest().body("Erro no sql: " + ex.getMessage());
    }

    @ExceptionHandler(TimeoutException.class)
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    public ResponseEntity<String> handleTimeException(TimeoutException time){
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Tempo de processamento de solicitação excedido"+ time.getMessage());    
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<String> handleAutentication(AuthenticationException authenticationException ){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválida");
    }

    @ExceptionHandler(JWTCreationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handlerJwtException(JWTCreationException JWTCreationException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na criação do token");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handlerInternoServidor(Exception ex){
        return ResponseEntity.internalServerError().body("Erro interno servidor");
    }

    @ExceptionHandler(JWTVerificationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<String> handlerValidatorJwt(JWTVerificationException JWTVerificationException){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
    }
}
