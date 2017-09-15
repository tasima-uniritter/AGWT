package com.uniritter.agwt.eventos;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.uniritter.agwt.eventos.domain.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by ALU201731064 on 01/09/2017.
 */

public class EventoTest {

    private Evento evento;
    private ValidadorEvento validadorEvento;
    private boolean ocorreuErro;

    @Before
    public void iniciar() {
        validadorEvento = new ValidadorEvento();
    }

    @Test
    public void criaEventoComNomeTest(){
        evento = new Evento("teste evento", LocalDate.now().plusDays(10));
        Assert.assertEquals(evento.getNome(), "teste evento");
    }

    @Test
    public void criaEventoComDataTest(){
        evento = new Evento("teste evento", LocalDate.now().plusDays(10));
        Assert.assertEquals(evento.getDataDoEvento(), LocalDate.now().plusDays(10));
    }


    @Test
    public void criaEventoValidaCampoNomeObrigatoriosTest() throws DataDoEventoNaoInformadoException {
        evento = new Evento("", null);
        ocorreuErro = false;

        try {
            validadorEvento.CamposObrigatorios(evento);
        } catch (NomeDoEventoNaoInformadoException e) {
            ocorreuErro = true;
        }

        Assert.assertTrue(ocorreuErro);
    }

    @Test
    public void criaEventoValidaCampoDataObrigatoriosTest() throws NomeDoEventoNaoInformadoException {
        evento = new Evento("Teste", null);
        ocorreuErro = false;

        try {
            validadorEvento.CamposObrigatorios(evento);
        } catch (DataDoEventoNaoInformadoException e) {
            ocorreuErro = true;
        }

        Assert.assertTrue(ocorreuErro);
    }

    @Test
    public void criaEventoCampoNomeMaiorQue150CaracteresTest(){
        evento = new Evento("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD" +
                "DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD",
                LocalDate.now().plusDays(1));

        ocorreuErro = false;

        try {
            validadorEvento.ValidaTamanhoCampoNome(evento);
        } catch (NomePermiteMax150CaracteresException e) {
            ocorreuErro = true;
        }

        Assert.assertTrue(ocorreuErro);
    }

    @Test
    public void criaEventoCampoDataIgualAHojeTest(){
        evento = new Evento("Teste", LocalDate.now());
        ocorreuErro = false;

        try {
            validadorEvento.ValidaCampoDataMenorIgualHoje(evento);
        } catch (DataMenorOuIgualAHojeException e) {
            ocorreuErro = true;
        }

        Assert.assertTrue(ocorreuErro);
    }

    @Test
    public void criaEventoCampoDataMenorQueHojeTest() {
        evento = new Evento("Teste", LocalDate.now().plusDays(-1));

        try {
            validadorEvento.ValidaCampoDataMenorIgualHoje(evento);
        } catch (DataMenorOuIgualAHojeException e) {
            ocorreuErro = true;
        }

        Assert.assertTrue(ocorreuErro);
    }
}


