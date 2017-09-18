package com.uniritter.agwt.eventos.domain.enumeration;

public enum IngressoTipoEnum {

    VIP ("VIP", Double.valueOf(1000) ),
    BACKSTAGE ("BACKSTAGE", Double.valueOf(800)),
    PLATEIA_VIP ("PLATEIA VIP", Double.valueOf(500)),
    PLATEIA ("PLATEIA", Double.valueOf(300));

    private final String nome;
    private final Double valor;

    IngressoTipoEnum(String nome, Double valor){
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public Double getValor() {
        return valor;
    }
}
