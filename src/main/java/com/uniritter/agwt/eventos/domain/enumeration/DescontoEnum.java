package com.uniritter.agwt.eventos.domain.enumeration;

public enum DescontoEnum {

    GERAL("GERAL", 1),
    GOLD("GOLD", 0.25),
    SILVER("SILVER", 0.4),
    ESTUDANTE("ESTUDANTE", 0.5);

    private String nome;
    private double desconto;

    DescontoEnum(String nome, double desconto){
        this.nome = nome;
        this.desconto = desconto;
    }

    public String getNome(){
        return this.nome;
    }

    public double getDesconto() {
        return desconto;
    }
}
