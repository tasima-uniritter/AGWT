package com.uniritter.agwt.eventos;

import com.uniritter.agwt.eventos.domain.Evento;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import java.util.Date;

/**
 * Created by ALU201731064 on 01/09/2017.
 */

public class EventoTest {

    @Test
    public void criaEventoTest(){

        Evento evento = new Evento("Evento Teste", new Date(2017,10,31));

        System.out.print(evento.toString());
        Assert.assertTrue("Sucesso", evento.getNome().equals("Evento Teste") );
    }

}


