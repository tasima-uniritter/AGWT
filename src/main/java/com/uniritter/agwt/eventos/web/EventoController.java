package com.uniritter.agwt.eventos.web;

import com.uniritter.agwt.eventos.domain.Evento;
import com.uniritter.agwt.eventos.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;

@Controller
@RequestMapping(value="/evento")
public class EventoController {

    @Autowired
    private EventoRepository repository;

    @RequestMapping(value="/")
    String home() {

        return "index";
    }

    @RequestMapping(value="/cadastra")
    String cadastra() {

        return "cadastro";
    }

    @RequestMapping(value="/cadastra/salva", method= RequestMethod.POST)
    String salva(@RequestParam String nome, @RequestParam LocalDate data, Model model) {

        System.out.println(nome);
        System.out.println(data);
        Evento evento = new Evento(nome, data);

        try {
            repository.save(evento);
            model.addAttribute("message","O evento " + evento.getNome()+ " foi cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println(new Date(System.currentTimeMillis())+ " INFO --- " + e.getMessage());
            model.addAttribute("message","O evento" + nome + " é invalido!");
        }

        return "salva";
    }

    @RequestMapping("/lista")
    public String listEventos(Model model){

        model.addAttribute("eventos", repository.findAll());

        return "lista";
    }
}
