package com.uniritter.agwt.eventos;

import com.uniritter.agwt.eventos.domain.Evento;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Classe de testes da entity Evento
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EventoDomainTest {

    @Test
    public void criaEventoComNomeTest(){
        Evento evento = new Evento();

        evento.setNome("Nome");

        Assert.assertTrue(evento.getNome().equals("Nome"));
    }
}


