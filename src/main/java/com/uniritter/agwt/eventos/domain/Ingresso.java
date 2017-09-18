package com.uniritter.agwt.eventos.domain;

import com.uniritter.agwt.eventos.domain.enumeration.IngressoTipoEnum;

import javax.persistence.*;

@Entity
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long codigo;

    @Column(nullable = false)
    private IngressoTipoEnum tipo;

    @ManyToOne
    private Evento evento;

    @Column(nullable = false)
    private Double valor;

    public Ingresso(IngressoTipoEnum tipo) {
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

    public IngressoTipoEnum getTipo() {
        return tipo;
    }

    public void setTipo(IngressoTipoEnum tipo) {
        this.tipo = tipo;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Double getValor() { return valor; }

    private void setValor(Double valor) { this.valor = valor; }
}
