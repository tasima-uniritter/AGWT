package com.uniritter.agwt.eventos.domain.validate;

import com.uniritter.agwt.eventos.domain.Evento;
import com.uniritter.agwt.eventos.domain.exception.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class EventoValidate {


    public boolean validate(Evento evento) throws DataDoEventoNaoInformadoException, NomeDoEventoNaoInformadoException, DataMenorOuIgualAHojeException, PeriodoVendaIngressosInvalidoException, NomePermiteMax150CaracteresException, TipoIngressoDuplicadoException {

        this.CamposObrigatorios(evento);
        this.ValidaCampoDataMenorIgualHoje(evento);
        this.ValidaPeriodoVendaIngressos(evento);
        this.ValidaTamanhoCampoNome(evento);
        this.ValidaTipoIngressoDuplicado(evento);

        return true;
    }

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

    public void ValidaTipoIngressoDuplicado(Evento evento) throws TipoIngressoDuplicadoException {
        if(evento != null){
            if(evento.getListaIngressoTipoEnum() != null){
                Set<Enum> set = new HashSet<Enum>();
                evento.getListaIngressoTipoEnum().stream().allMatch(o -> set.add(o));

                if(set.size() != evento.getListaIngressoTipoEnum().size())
                    throw new TipoIngressoDuplicadoException();
            }
        }
    }
}
