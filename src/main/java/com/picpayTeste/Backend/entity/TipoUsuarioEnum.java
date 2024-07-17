package com.picpayTeste.Backend.entity;

public enum TipoUsuarioEnum {
    LOJISTA("ROLE_LOJISTA"),
    USUARIO("ROLE_USUARIO");

    private String tipo;


    private TipoUsuarioEnum(String tipo){
        this.tipo=tipo;
    }
 
    public String getTipo(){
        return tipo;
    }
}
