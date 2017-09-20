package com.uniritter.agwt.eventos.resource;

import com.uniritter.agwt.eventos.domain.Evento;
import com.uniritter.agwt.eventos.domain.exception.*;
import com.uniritter.agwt.eventos.repository.EventoRepository;
import com.uniritter.agwt.eventos.service.EventoService;
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
    private EventoService service;

    @RequestMapping("/incluir")
    public void incluirEvento(@RequestParam String nome, @RequestParam LocalDate data) throws URISyntaxException {
        System.out.println(nome);
        System.out.println(data);
        Evento evento = new Evento();

        evento.setNome(nome);
        evento.setDataDoEvento(data);

        try {

            service.save(evento);
        } catch (NomeDoEventoNaoInformadoException e) {
            e.printStackTrace();
        } catch (DataDoEventoNaoInformadoException e) {
            e.printStackTrace();
        } catch (DataMenorOuIgualAHojeException e) {
            e.printStackTrace();
        } catch (NomePermiteMax150CaracteresException e) {
            e.printStackTrace();
        } catch (TipoIngressoDuplicadoException e) {
            e.printStackTrace();
        } catch (PeriodoVendaIngressosInvalidoException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/lista")
    public List<Evento> listEventos(){
        return service.findAll();
    }


}
