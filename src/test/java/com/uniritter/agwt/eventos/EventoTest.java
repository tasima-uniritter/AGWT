package com.uniritter.agwt.eventos;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.uniritter.agwt.eventos.domain.*;
import org.apache.tomcat.jni.Local;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ALU201731064 on 01/09/2017.
 */

public class EventoTest {

    private Evento evento;
    private ValidadorEvento validadorEvento;
    private boolean ocorreuErro;
    private List<IngressoTipo> listaIngressoTipo;

    @Before
    public void iniciar() {
        validadorEvento = new ValidadorEvento();
        listaIngressoTipo = new ArrayList<IngressoTipo>();
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
        ocorreuErro = false;

        try {
            validadorEvento.ValidaCampoDataMenorIgualHoje(evento);
        } catch (DataMenorOuIgualAHojeException e) {
            ocorreuErro = true;
        }

        Assert.assertTrue(ocorreuErro);
    }

    @Test
    public void criaEventoDefinindoPeriodoDeVendaTest(){
        evento = new Evento("Teste",
                LocalDate.now().plusDays(10),
                LocalDate.now(),
                LocalDate.now());

        boolean foiInformado = false;
        if(evento.getInicioVendas() != null && evento.getFinalVendas() != null)
            foiInformado = true;

        Assert.assertTrue("Foi definido o período", foiInformado);

    }

    @Test
    public void criaEventoDataInicioMenorQueDataFimPeriodoTest(){
        evento = new Evento("Teste",
                LocalDate.now().plusDays(10),
                LocalDate.now().plusDays(1),
                LocalDate.now());

        ocorreuErro = false;

        try {
            validadorEvento.ValidaPeriodoVendaIngressos(evento);
        } catch (PeriodoVendaIngressosInvalidoException e) {
            ocorreuErro = true;
        }

        Assert.assertTrue("Validou o periodo de venda de ingressos", ocorreuErro);
    }

    @Test
    public void criaEventoDataInicioMaiorQueDataFimPeriodoTest(){
        evento = new Evento("Teste",
                LocalDate.now().plusDays(10),
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2));

        ocorreuErro = false;

        try {
            validadorEvento.ValidaPeriodoVendaIngressos(evento);
        } catch (PeriodoVendaIngressosInvalidoException e) {
            ocorreuErro = true;
        }

        Assert.assertFalse("Validou o periodo de venda de ingressos", ocorreuErro);
    }

    /*
    Critério 3
    Dado que quero vincular os tipos de ingressos existentes ao evento
    Quando cadastrar o evento
    Então quero definir quais tipos de ingressos estarão disponíveis
    */
    @Test
    public void criaEventoComTiposDeIngressoTest(){
        listaIngressoTipo.add(IngressoTipo.VIP);
        listaIngressoTipo.add(IngressoTipo.PLATEIA_VIP);
        listaIngressoTipo.add(IngressoTipo.PLATEIA);

        evento = new Evento("Teste",
                LocalDate.now().plusDays(10),
                LocalDate.now(),
                LocalDate.now(),
                listaIngressoTipo);

        List<IngressoTipo> listaValidacao = new ArrayList<>();
        listaValidacao.add(IngressoTipo.VIP);
        listaValidacao.add(IngressoTipo.PLATEIA_VIP);
        listaValidacao.add(IngressoTipo.PLATEIA);

        Assert.assertTrue("Adicionou tipos de ingresso corretamente", listaValidacao.containsAll(listaIngressoTipo));
    }

    @Test
    public void criaEventoComTiposDeIngressoErroTest(){
        listaIngressoTipo.add(IngressoTipo.VIP);
        listaIngressoTipo.add(IngressoTipo.PLATEIA_VIP);
        listaIngressoTipo.add(IngressoTipo.PLATEIA);

        evento = new Evento("Teste",
                LocalDate.now().plusDays(10),
                LocalDate.now(),
                LocalDate.now(),
                listaIngressoTipo);

        List<IngressoTipo> listaValidacao = new ArrayList<>();
        listaValidacao.add(IngressoTipo.VIP);
        listaValidacao.add(IngressoTipo.PLATEIA_VIP);

        Assert.assertFalse("Adicionou tipos de ingresso corretamente", listaValidacao.containsAll(listaIngressoTipo));
    }

    /*Critério 4
    Dado que quero criar um evento com ingressos
    Quando salvar e ocorrer tipos de ingressos duplicados
    Então sistema não deve permitir salvar
    */

    @Test
    public void criarEventoComTipoDeIngressoDuplicadoTest(){
        listaIngressoTipo.add(IngressoTipo.VIP);
        listaIngressoTipo.add(IngressoTipo.PLATEIA_VIP);
        listaIngressoTipo.add(IngressoTipo.PLATEIA);
        listaIngressoTipo.add(IngressoTipo.PLATEIA);

        evento = new Evento("Teste",
                LocalDate.now().plusDays(10),
                LocalDate.now(),
                LocalDate.now(),
                listaIngressoTipo);

        validadorEvento.ValidaTipoIngressoDuplicado(evento);
    }
}


