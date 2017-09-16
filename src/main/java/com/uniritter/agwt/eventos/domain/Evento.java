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

    @Column(nullable = false)
    private LocalDate inicioVendas;

    @Column(nullable = false)
    private LocalDate finalVendas;

    @Column(nullable = false)
    private List<IngressoTipo> listaIngressoTipo;

    public Evento(String nome, LocalDate dataDoEvento) {
        setNome(nome);
        setDataDoEvento(dataDoEvento);
    }

    public Evento(String nome, LocalDate dataDoEvento, LocalDate inicioVendas, LocalDate finalVendas) {
        setNome(nome);
        setDataDoEvento(dataDoEvento);
        setInicioVendas(inicioVendas);
        setFinalVendas(finalVendas);
    }

    public Evento(String teste, LocalDate localDate, LocalDate now, LocalDate date, List<IngressoTipo> listaIngressoTipo){
        setNome(nome);
        setDataDoEvento(dataDoEvento);
        setInicioVendas(inicioVendas);
        setFinalVendas(finalVendas);
        setListaIngressoTipo(listaIngressoTipo);
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

    public List<Ingresso> getIngressos() { return ingressos; }

    public void setIngressos(List<Ingresso> ingressos) { this.ingressos = ingressos; }

    public LocalDate getInicioVendas() { return inicioVendas; }

    public void setInicioVendas(LocalDate inicioVendas) { this.inicioVendas = inicioVendas; }

    public LocalDate getFinalVendas() { return finalVendas; }

    public void setFinalVendas(LocalDate finalVendas) { this.finalVendas = finalVendas; }

    public List<IngressoTipo> getListaIngressoTipo() {
        return listaIngressoTipo;
    }

    public void setListaIngressoTipo(List<IngressoTipo> listaIngressoTipo) {
        this.listaIngressoTipo = listaIngressoTipo;
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
