package com.uniritter.agwt.eventos;

import com.uniritter.agwt.eventos.domain.Ingresso;
import com.uniritter.agwt.eventos.domain.enumeration.IngressoTipoEnum;
import org.junit.Assert;
import org.junit.Test;

public class IngressoTest {

    private Ingresso ingresso;

    @Test
    public void criaIngressoVipTest(){
        ingresso = new Ingresso(IngressoTipoEnum.VIP);
        Assert.assertEquals("Valor é 1000.", ingresso.getValor(), Double.valueOf(1000));
    }

    @Test
    public void criaIngressoBackstageTest(){
        ingresso = new Ingresso(IngressoTipoEnum.BACKSTAGE);
        Assert.assertEquals("Valor é 800.", ingresso.getValor(), Double.valueOf(800));
    }

    @Test
    public void criaIngressoPlateiaVipTest(){
        ingresso = new Ingresso(IngressoTipoEnum.PLATEIA_VIP);
        Assert.assertEquals("Valor é 500.", ingresso.getValor(), Double.valueOf(500));
    }

    @Test
    public void criaIngressoPlateiaTest(){
        ingresso = new Ingresso(IngressoTipoEnum.PLATEIA);
        Assert.assertEquals("Valor é 300.", ingresso.getValor(), Double.valueOf(300));
    }
}
