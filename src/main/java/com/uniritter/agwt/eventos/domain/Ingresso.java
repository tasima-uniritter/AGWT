package com.uniritter.agwt.eventos.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long codigo;

    @Column(nullable = false)
    private IngressoTipo tipo;

    @ManyToOne
    private Evento evento;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private LocalDate inicioVendas;

    @Column(nullable = false)
    private LocalDate finalVendas;

    public Ingresso(IngressoTipo tipo) {
        this.tipo = tipo;
        atribuiValorDosIngressos();
    }

    private void atribuiValorDosIngressos(){
        switch (tipo){
            case VIP:
                setValor(Double.valueOf(1000));
                break;
            case BACKSTAGE:
                setValor(Double.valueOf(800));
                break;
            case PLATEIA_VIP:
                setValor(Double.valueOf(500));
                break;
            case PLATEIA:
                setValor(valor = Double.valueOf(300));
                break;
        }
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public IngressoTipo getTipo() {
        return tipo;
    }

    public void setTipo(IngressoTipo tipo) {
        this.tipo = tipo;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Double getValor() { return valor; }

    private void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getInicioVendas() {
        return inicioVendas;
    }

    public void setInicioVendas(LocalDate inicioVendas) {
        this.inicioVendas = inicioVendas;
    }

    public LocalDate getFinalVendas() {
        return finalVendas;
    }

    public void setFinalVendas(LocalDate finalVendas) {
        this.finalVendas = finalVendas;
    }
}
