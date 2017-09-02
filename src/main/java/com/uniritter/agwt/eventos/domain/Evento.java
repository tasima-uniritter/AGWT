package com.uniritter.agwt.eventos.domain;

import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ALU201731064 on 01/09/2017.
 */
@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long Id;

    @Column(length = 150, nullable = false)
    private String nome;

    @Column(nullable = false)
    private Date dataDoEvento;

    public Evento(String nome, Date dataDoEvento){
        setNome(nome);
        setDataDoEvento(dataDoEvento);
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
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
                "Id=" + Id +
                ", nome='" + nome + '\'' +
                ", dataDoEvento=" + dataDoEvento +
                '}';
    }
}
