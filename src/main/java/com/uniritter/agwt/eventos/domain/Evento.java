package com.uniritter.agwt.eventos.domain;

import java.util.Date;

/**
 * Created by ALU201731064 on 01/09/2017.
 */
public class Evento {
    private String nome;
    private Date dataDoEvento;

    public Evento(String nome, Date dataDoEvento){
        setNome(nome);
        setDataDoEvento(dataDoEvento);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataDoEvento() {
        return dataDoEvento;
    }

    public void setDataDoEvento(Date dataDoEvento) {
        this.dataDoEvento = dataDoEvento;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "nome='" + nome + '\'' +
                ", dataDoEvento=" + dataDoEvento +
                '}';
    }
}
