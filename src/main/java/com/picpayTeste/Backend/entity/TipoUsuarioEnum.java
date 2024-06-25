package com.picpayTeste.Backend.entity;

public enum TipoEnum {
    Lojista("lojista"),
    Usuario("usuario");

    private String tipo;


    private TipoEnum(String tipo){
        this.tipo=tipo;
    }
 
    public String getTipo(){
        return tipo;
    }
}
