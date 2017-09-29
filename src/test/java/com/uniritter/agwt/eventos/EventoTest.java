package com.uniritter.agwt.eventos;

import com.uniritter.agwt.eventos.domain.*;
import com.uniritter.agwt.eventos.domain.enumeration.IngressoTipoEnum;
import com.uniritter.agwt.eventos.domain.exception.*;
import com.uniritter.agwt.eventos.domain.validate.EventoValidate;
import com.uniritter.agwt.eventos.service.EventoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de testes da entity Evento
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EventoTest {

    private Evento evento;
    private EventoValidate validadorEvento;
    private boolean ocorreuErro;
    private List<IngressoTipoEnum> listaIngressoTipoEnum;

    @Autowired
    private EventoService service;

    @Before
    public void iniciar() {
        validadorEvento = new EventoValidate();
        listaIngressoTipoEnum = new ArrayList<IngressoTipoEnum>();
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

    /**
     * US #1 - Criar eventos
     *
     * Critério 1
     *
     * Dado que quero criar um evento
     * Quando  carregar o formulário
     * Então deve apresentar os seguintes campos:
     * - Nome
     * - Data do evento
     *
     * Critério 2
     *
     * Dado que quero criar um evento
     * Quando salvar o evento
     * Então os campos abaixo devem ser obrigatórios:
     * - Nome
     * - Data do evento
     *
     */
    @Test
    public void salvaEventoSemNome() {

        Boolean erroNome = false;

        Evento evento = new Evento();
        evento.setDataDoEvento(LocalDate.now());

        try {
            service.save(evento);
        } catch (NomeDoEventoNaoInformadoException e) {
            erroNome = true;
        } catch (DataDoEventoNaoInformadoException e) {
        } catch (DataMenorOuIgualAHojeException e) {
        } catch (NomePermiteMax150CaracteresException e) {
        } catch (TipoIngressoDuplicadoException e) {
        } catch (PeriodoVendaIngressosInvalidoException e) {
        }

        Assert.assertTrue(erroNome);
    }

    @Test
    public void salvaEventoSemDataEvento() throws NomeDoEventoNaoInformadoException {
        Boolean erroData = false;

        Evento evento = new Evento();
        evento.setNome("evento teste");

        try {
            service.save(evento);
        } catch (NomeDoEventoNaoInformadoException e) {
        } catch (DataDoEventoNaoInformadoException e) {
            erroData = true;
        } catch (DataMenorOuIgualAHojeException e) {
        } catch (NomePermiteMax150CaracteresException e) {
        } catch (TipoIngressoDuplicadoException e) {
        } catch (PeriodoVendaIngressosInvalidoException e) {
        }

        Assert.assertTrue(erroData);
    }

    /**
     * Critério 3
     *
     * Dado que quero criar um evento
     * Quando informar um nome maior que
     * caracteres e salvar
     * Então não deve permitir salvar e informar a mensagem:
     * "O nome permite no máximo 150 caracteres"
     *
     */
    @Test
    public void salvaEventoComNomeMaiorQue150Caracteres(){
        Evento evento = new Evento();
        try{

            evento.setNome("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD" +
                "DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
            evento.setDataDoEvento(LocalDate.now());
            service.save(evento);
            Assert.assertTrue(false);

        } catch (DataDoEventoNaoInformadoException e) {
        } catch (NomePermiteMax150CaracteresException e) {


            Assert.assertTrue((e.getMessage() == "O nome permite no máximo 150 caracteres"));
        } catch (PeriodoVendaIngressosInvalidoException e) {
        } catch (NomeDoEventoNaoInformadoException e) {
        } catch (TipoIngressoDuplicadoException e) {
        } catch (DataMenorOuIgualAHojeException e) {
        }
    }

    /**
     * Critério 4
     * Dado que quero criar um evento
     * Quando informar uma data inferior ao do dia atual e salvar
     * Então não deve permitir salvar e informar a mensagem:
     * "A data do evento deve ser igual ou maior que a de hoje"
     */
    @Test
    public void salvaEventoComDataEventoInferiorAoDiaAtual() {

        Evento evento = new Evento();
        evento.setNome("Teste");
        evento.setDataDoEvento(LocalDate.now().minusDays(1));

        try {
            service.save(evento);
        } catch (DataMenorOuIgualAHojeException e) {
            Assert.assertTrue(e.getMessage() == "A data do evento deve ser igual ou maior que a de hoje");
        } catch (DataDoEventoNaoInformadoException e) {
        } catch (NomePermiteMax150CaracteresException e) {
        } catch (PeriodoVendaIngressosInvalidoException e) {
        } catch (NomeDoEventoNaoInformadoException e) {
        } catch (TipoIngressoDuplicadoException e) {
        }
    }

    /**
     * US #3 - Disponibilizar Ingressos
     *
     * Critério 1
     *
     * Dado que pretendo disponibilizar venda online
     * Quando cadastrar o evento
     * Então quero definir um período de venda
     *
     */
    @Test
    public void salvarEventoComPeriodoDeVenda(){

        Evento evento = new Evento();

        evento.setNome("Teste");
        evento.setDataDoEvento(LocalDate.now().plusDays(10));
        evento.setInicioVendas(LocalDate.now());
        evento.setFinalVendas(LocalDate.now());

        Evento eventoSalvo = new Evento();;
        try {
            eventoSalvo = service.save(evento);
        } catch (NomeDoEventoNaoInformadoException e) {
        } catch (DataDoEventoNaoInformadoException e) {
        } catch (DataMenorOuIgualAHojeException e) {
        } catch (NomePermiteMax150CaracteresException e) {
        } catch (TipoIngressoDuplicadoException e) {
        } catch (PeriodoVendaIngressosInvalidoException e) {
        }

        boolean foiInformado = false;
        if(eventoSalvo.getInicioVendas() != null && eventoSalvo.getFinalVendas() != null)
            foiInformado = true;

        Assert.assertTrue("Foi definido o período", foiInformado);

    }

    /*Critério 2
    Dado que quero criar um evento
    Quando informar data de início de venda posterior a data fim de venda do evento
    Então sistema não deve permitir salvar e informa a mensagem:
    "A data de início de venda deve ser inferior a data de fim"
    */

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
        listaIngressoTipoEnum.add(IngressoTipoEnum.VIP);
        listaIngressoTipoEnum.add(IngressoTipoEnum.PLATEIA_VIP);
        listaIngressoTipoEnum.add(IngressoTipoEnum.PLATEIA);

        evento = new Evento("Teste",
                LocalDate.now().plusDays(10),
                LocalDate.now(),
                LocalDate.now(),
                listaIngressoTipoEnum);

        System.out.println(evento.toString());

        List<IngressoTipoEnum> listaValidacao = new ArrayList<>();
        listaValidacao.add(IngressoTipoEnum.VIP);
        listaValidacao.add(IngressoTipoEnum.PLATEIA_VIP);
        listaValidacao.add(IngressoTipoEnum.PLATEIA);

        Assert.assertTrue("Adicionou tipos de ingresso corretamente", listaValidacao.containsAll(listaIngressoTipoEnum));
    }

    @Test
    public void criaEventoComTiposDeIngressoErroTest(){
        listaIngressoTipoEnum.add(IngressoTipoEnum.VIP);
        listaIngressoTipoEnum.add(IngressoTipoEnum.PLATEIA_VIP);
        listaIngressoTipoEnum.add(IngressoTipoEnum.PLATEIA);

        evento = new Evento("Teste",
                LocalDate.now().plusDays(10),
                LocalDate.now(),
                LocalDate.now(),
                listaIngressoTipoEnum);

        List<IngressoTipoEnum> listaValidacao = new ArrayList<>();
        listaValidacao.add(IngressoTipoEnum.VIP);
        listaValidacao.add(IngressoTipoEnum.PLATEIA_VIP);

        Assert.assertFalse("Adicionou tipos de ingresso corretamente", listaValidacao.containsAll(listaIngressoTipoEnum));
    }

    /*Critério 4
    Dado que quero criar um evento com ingressos
    Quando salvar e ocorrer tipos de ingressos duplicados
    Então sistema não deve permitir salvar
    */

    @Test
    public void criarEventoComTipoDeIngressoDuplicadoTest(){
        listaIngressoTipoEnum.add(IngressoTipoEnum.VIP);
        listaIngressoTipoEnum.add(IngressoTipoEnum.PLATEIA_VIP);
        listaIngressoTipoEnum.add(IngressoTipoEnum.PLATEIA);
        listaIngressoTipoEnum.add(IngressoTipoEnum.PLATEIA);

        evento = new Evento("Teste",
                LocalDate.now().plusDays(10),
                LocalDate.now(),
                LocalDate.now(),
                listaIngressoTipoEnum);

        ocorreuErro = false;

        try {
            validadorEvento.ValidaTipoIngressoDuplicado(evento);
        } catch (TipoIngressoDuplicadoException e) {
            ocorreuErro = true;
        }

        Assert.assertTrue("Não é possível criar um evento com o mesmo tipo de ingresso", ocorreuErro);
    }
}


