package com.uniritter.agwt.eventos.domain;

import java.time.LocalDate;

public class ValidadorEvento {

    public void CamposObrigatorios(Evento evento) throws NomeDoEventoNaoInformadoException, DataDoEventoNaoInformadoException {
        if(evento != null){
            if(evento.getNome() == null || evento.getNome().equals(""))
                throw new NomeDoEventoNaoInformadoException();

            if(evento.getDataDoEvento() == null)
                throw new DataDoEventoNaoInformadoException();
        }
    }

    public void ValidaTamanhoCampoNome(Evento evento) throws NomePermiteMax150CaracteresException {
        if(evento != null){
            if(evento.getNome().length() > 150)
                throw new NomePermiteMax150CaracteresException();
        }
    }

    public void ValidaCampoDataMenorIgualHoje(Evento evento) throws DataMenorOuIgualAHojeException {
        if(evento != null){
            if(evento.getDataDoEvento().isBefore(LocalDate.now()) ||
                    evento.getDataDoEvento().isEqual(LocalDate.now()))
                throw new DataMenorOuIgualAHojeException();
        }
    }

    public void ValidaPeriodoVendaIngressos(Evento evento) throws PeriodoVendaIngressosInvalidoException {
        if(evento != null){
            if(evento.getInicioVendas() != null && evento.getFinalVendas() != null &&
                    evento.getInicioVendas().isAfter(evento.getFinalVendas())){
                throw new PeriodoVendaIngressosInvalidoException("A data de início de venda deve ser inferior a data de fim.");
            }
        }
    }

    public void ValidaTipoIngressoDuplicado(Evento evento) {
        if(evento != null){
            //if(evento.getListaIngressoTipo() != null){
            //     evento.getListaIngressoTipo()
            //}
        }
    }
}
