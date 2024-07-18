package com.picpayTeste.Backend.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Value("${serviceStatus}")
    private String verificacaoService;

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrar(@RequestBody @Validated Usuario usuario){
                usuarioService.registrar(usuario);
                return ResponseEntity.ok().build();
    }


    @Transactional(rollbackOn = Exception.class)
    @PostMapping(path = "/transferir")
    public ResponseEntity<String> transferenciaValor(@RequestBody TransferenciaValor transferenciaValor ) throws JsonMappingException, JsonProcessingException{

        Usuario usuario = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        String status="false";

        ResponseEntity<String> respostaServiceAtivo = restTemplate.getForEntity(verificacaoService, String.class);

        if(respostaServiceAtivo.getStatusCode().value()==200){
            String respostaAuthorization=respostaServiceAtivo.getBody();

            JsonNode jsonNode = mapper.readTree(respostaAuthorization);
            status = jsonNode.get("authorization").asText();

        }if(status=="true"){
            if(transferenciaValor.getDestinatario().length()==14){
                    Usuario destinatario = usuarioRepository.findByCpf(transferenciaValor.getDestinatario());
                if(destinatario!=null){ 
                    if(usuario.getCarteira().compareTo(transferenciaValor.getValor())<0){
                        return ResponseEntity.badRequest().body("Saldo insuficiente");
                    }
                    usuarioService.transferenciaUsuario(usuario, destinatario, transferenciaValor.getValor());
                }
            }else if(transferenciaValor.getDestinatario().length()==18){

                Lojista lojista= lojistaRepository.findByCnpj(transferenciaValor.getDestinatario());   
                if(lojista!=null){
                    if(usuario.getCarteira().compareTo(transferenciaValor.getValor())<0){
                        return ResponseEntity.badRequest().body("Saldo insuficiente");
                } 
                }
                 usuarioService.transferenciaLojista(usuario, lojista, transferenciaValor.getValor());
            }else{
                return ResponseEntity.badRequest().body("Destinatário não encontrado");
            }
         }
         return ResponseEntity.ok("Transação realizada com sucesso");
        }
        
     }

