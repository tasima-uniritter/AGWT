package com.uniritter.agwt.eventos.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
    private LocalDate dataDoEvento;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private List<Ingresso> ingressos;

    public Evento(String nome, LocalDate dataDoEvento){
        setNome(nome);
        setDataDoEvento(dataDoEvento);
    }

    public Evento(){

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

    public LocalDate getDataDoEvento() {
        return dataDoEvento;
    }

    public void setDataDoEvento(LocalDate dataDoEvento) {
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
