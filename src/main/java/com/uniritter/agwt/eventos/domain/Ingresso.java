package com.uniritter.agwt.eventos.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long codigo;

    @Column
    private IngressoTipo tipo;

    @Column
    private Evento evento;

    @Column
    private Double valor;

    @Column
    private LocalDate inicioVendas;

    @Column
    private LocalDate finalVendas;

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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
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
