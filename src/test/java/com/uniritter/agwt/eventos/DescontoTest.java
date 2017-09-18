package com.uniritter.agwt.eventos;

import com.uniritter.agwt.eventos.domain.*;
import com.uniritter.agwt.eventos.domain.interfaces.IDesconto;
import org.junit.Assert;
import org.junit.Test;

public class DescontoTest {
    private IDesconto desconto;

    //US #4 - Disponibilizar Descontos

    /*
    Critério 1
    Dado que sou estudante
    Quando for calcular valor do ingresso
    Então deverá dar 50% de desconto
     */

    @Test
    public void aplicaDescontoEstudanteTest(){
        desconto = new DescontoEstudante();
        Double valorComDesconto = desconto.CalculaDesconto(Double.valueOf(100));

        Assert.assertEquals("Desconto estudante", valorComDesconto, Double.valueOf(50));
    }

    /*
    Critério 2
    Dado que sou idoso
    Quando for calcular valor do ingresso
    Então deverá dar 50% de desconto
     */

    @Test
    public void aplicaDescontoIdosoTest(){
        desconto = new DescontoIdoso();
        Double valorComDesconto = desconto.CalculaDesconto(Double.valueOf(100));

        Assert.assertEquals("Desconto idoso", valorComDesconto, Double.valueOf(50));
    }

    /*
    Critério 3
    Dado que sou membro gold
    Quando for calcular valor do ingresso
    Então deverá dar 75% de desconto
     */

    @Test
    public void aplicaDescontoGoldTest(){
        desconto = new DescontoGold();
        Double valorComDesconto = desconto.CalculaDesconto(Double.valueOf(100));

        Assert.assertEquals("Desconto membroGold", valorComDesconto, Double.valueOf(25));
    }

    /*
    Critério 4
    Dado que sou membro silver
    Quando for calcular valor do ingresso
    Então deverá dar 60% de desconto
     */

    @Test
    public void aplicaDescontoSilverTest(){
        desconto = new DescontoSilver();
        Double valorComDesconto = desconto.CalculaDesconto(Double.valueOf(100));

        Assert.assertEquals("Desconto membro silver", valorComDesconto, Double.valueOf(40));
    }

    /*
    Critério 5
    Dado que sou público geral
    Quando for calcular valor do ingresso
    Então não deverá dar nenhum tipo de desconto
     */

    @Test
    public void aplicaDescontoPublicoGeralTest(){
        desconto = new DescontoGeral();
        Double valorComDesconto = desconto.CalculaDesconto(Double.valueOf(100));

        Assert.assertEquals("Desconto publico geral", valorComDesconto, Double.valueOf(100));
    }
}
