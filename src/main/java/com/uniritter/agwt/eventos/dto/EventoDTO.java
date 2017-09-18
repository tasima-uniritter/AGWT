package com.uniritter.agwt.eventos.dto;

import com.uniritter.agwt.eventos.domain.enumeration.IngressoTipoEnum;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

public class EventoDTO {

    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private long Id;

    private String nome;

    private LocalDate dataDoEvento;

    private LocalDate inicioVendas;

    private LocalDate finalVendas;

    private List<IngressoTipoEnum> listaIngressoTipoEnum;

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

    public List<IngressoTipoEnum> getListaIngressoTipoEnum() {
        return listaIngressoTipoEnum;
    }

    public void setListaIngressoTipoEnum(List<IngressoTipoEnum> listaIngressoTipoEnum) {
        this.listaIngressoTipoEnum = listaIngressoTipoEnum;
    }
}
