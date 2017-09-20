package com.uniritter.agwt.eventos.service;

import com.uniritter.agwt.eventos.domain.Evento;
import com.uniritter.agwt.eventos.domain.exception.*;
import com.uniritter.agwt.eventos.domain.validate.EventoValidate;
import com.uniritter.agwt.eventos.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repository;

    public Evento save(Evento evento) throws NomeDoEventoNaoInformadoException, DataDoEventoNaoInformadoException, DataMenorOuIgualAHojeException, NomePermiteMax150CaracteresException, TipoIngressoDuplicadoException, PeriodoVendaIngressosInvalidoException {

        EventoValidate validate = new EventoValidate();
        validate.validate(evento);

        return repository.save(evento);
    }

    public List<Evento> findAll(){
        return repository.findAll();
    }

    public Evento findOne(long id) {
        return repository.findOne(id);
    }
}
