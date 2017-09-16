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
import java.util.List;

@Controller
@RequestMapping(value="/evento")
public class EventoController {

    @Autowired
    private EventoRepository repository;

    @RequestMapping(value="/cadastro")
    String cadastra() {

        return "/evento/cadastro";
    }

    @RequestMapping(value="/cadastro/salva", method= RequestMethod.POST)
    String salva(@RequestParam String nome, @RequestParam String data, Model model) {

        System.out.println(nome);
        System.out.println(data);

        try {
            String[] parts = data.split("-");
            Integer ano = Integer.valueOf(parts[0]);
            Integer mes = Integer.valueOf(parts[1]);
            Integer dia = Integer.valueOf(parts[2]);

            Evento evento = new Evento(nome, LocalDate.of(ano,mes,dia));

            repository.save(evento);
            model.addAttribute("message","O evento " + evento.getNome()+ " foi cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println(new Date(System.currentTimeMillis())+ " INFO --- " + e.getMessage());
            model.addAttribute("message","O evento" + nome + " é invalido!");
        }

        return "/evento/cadastro";
    }

    @RequestMapping("/lista")
    public String listEventos(Model model){

        List<Evento> eventos = repository.findAll();

        System.out.println(eventos);

        model.addAttribute("eventos", eventos);

        return "/evento/lista";
    }
}
