package com.picpayTeste.Backend.entity;

public enum TipoUsuarioEnum {
    Lojista("lojista"),
    Usuario("usuario");

    private String tipo;


    private TipoUsuarioEnum(String tipo){
        this.tipo=tipo;
    }
 
    public String getTipo(){
        return tipo;
    }
}
