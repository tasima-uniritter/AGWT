package com.uniritter.agwt.eventos;

import com.uniritter.agwt.eventos.domain.Ingresso;
import com.uniritter.agwt.eventos.domain.enumeration.IngressoTipoEnum;
import org.junit.Assert;
import org.junit.Test;

public class IngressoTest {

    private Ingresso ingresso;

    //US #2 - Ingressos

    /*Critério 1
    Dado que quero disponibilizar um ingresso VIP
    Quando informar o valor
    Então o mesmo deve ser 1000
    */

    @Test
    public void criaIngressoVipTest(){
        ingresso = new Ingresso(IngressoTipoEnum.VIP);
        Assert.assertEquals("Valor é 1000.", ingresso.getValor(), Double.valueOf(1000));
    }

    /*Critério 2
    Dado que quero disponibilizar um ingresso BACKSTAGE
    Quando informar o valor
    Então o mesmo deve ser 800
    */

    @Test
    public void criaIngressoBackstageTest(){
        ingresso = new Ingresso(IngressoTipoEnum.BACKSTAGE);
        Assert.assertEquals("Valor é 800.", ingresso.getValor(), Double.valueOf(800));
    }

    /*Critério 3
    Dado que quero disponibilizar um ingresso PLATEIA VIP
    Quando informar o valor
    Então o mesmo deve ser 500
    */

    @Test
    public void criaIngressoPlateiaVipTest(){
        ingresso = new Ingresso(IngressoTipoEnum.PLATEIA_VIP);
        Assert.assertEquals("Valor é 500.", ingresso.getValor(), Double.valueOf(500));
    }

    /*Critério 4
    Dado que quero disponibilizar um ingresso PLATEIA
    Quando informar o valor
    Então o mesmo deve ser 300
    */

    @Test
    public void criaIngressoPlateiaTest(){
        ingresso = new Ingresso(IngressoTipoEnum.PLATEIA);
        Assert.assertEquals("Valor é 300.", ingresso.getValor(), Double.valueOf(300));
    }
}
