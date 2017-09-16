package com.uniritter.agwt.eventos.resource;

import com.uniritter.agwt.eventos.domain.Evento;
import com.uniritter.agwt.eventos.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/evento")
public class EventoResource {

    @Autowired
    private EventoRepository repository;


    @RequestMapping("/incluir")
    public Evento incluirEvento(@RequestParam String nome, @RequestParam LocalDate data) throws URISyntaxException {
        System.out.println(nome);
        System.out.println(data);
        Evento evento = new Evento(nome, data);

        return repository.save(evento);
    }

    @RequestMapping("/lista")
    public List<Evento> listEventos(){
        return repository.findAll();
    }


}
