package com.uniritter.agwt.eventos.domain;

import com.uniritter.agwt.eventos.domain.enumeration.IngressoTipoEnum;

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

    @ElementCollection(fetch = FetchType.LAZY, targetClass = IngressoTipoEnum.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    @JoinTable(name = "evento_tipo")
    private List<IngressoTipoEnum> listaIngressoTipoEnum;

    public Evento() {

    }

    public Evento(String nome, LocalDate dataDoEvento) {
        this.nome = nome;
        this.dataDoEvento = dataDoEvento;
    }

    public Evento(String nome, LocalDate dataDoEvento, LocalDate inicioVendas, LocalDate finalVendas) {
        this.nome = nome;
        this.dataDoEvento = dataDoEvento;
        this.inicioVendas = inicioVendas;
        this.finalVendas = finalVendas;
    }

    public Evento(String nome, LocalDate dataDoEvento, LocalDate inicioVendas, LocalDate finalVendas, List<IngressoTipoEnum> listaIngressoTipoEnum){
        this.nome = nome;
        this.dataDoEvento = dataDoEvento;
        this.inicioVendas = inicioVendas;
        this.finalVendas = finalVendas;
        this.listaIngressoTipoEnum = listaIngressoTipoEnum;
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

    public List<IngressoTipoEnum> getListaIngressoTipoEnum() {
        return listaIngressoTipoEnum;
    }

    public void setListaIngressoTipoEnum(List<IngressoTipoEnum> listaIngressoTipoEnum) {
        this.listaIngressoTipoEnum = listaIngressoTipoEnum;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "Id=" + Id +
                ", nome='" + nome + '\'' +
                ", dataDoEvento=" + dataDoEvento +
                ", ingressos=" + ingressos +
                ", inicioVendas=" + inicioVendas +
                ", finalVendas=" + finalVendas +
                ", listaIngressoTipoEnum=" + listaIngressoTipoEnum +
                '}';
    }
}
