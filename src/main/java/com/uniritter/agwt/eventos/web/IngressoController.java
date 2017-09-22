package com.uniritter.agwt.eventos.web;

import com.uniritter.agwt.eventos.domain.Evento;
import com.uniritter.agwt.eventos.domain.Ingresso;
import com.uniritter.agwt.eventos.domain.enumeration.IngressoTipoEnum;
import com.uniritter.agwt.eventos.repository.EventoRepository;
import com.uniritter.agwt.eventos.repository.IngressoRepository;
import com.uniritter.agwt.eventos.service.EventoService;
import com.uniritter.agwt.eventos.service.IngressoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/ingresso")
public class IngressoController {

    @Autowired
    private IngressoService ingressoService;

    @Autowired
    private EventoService eventoService;

    @RequestMapping
    String principal(Model model) {

        this.getEventos(model);
        this.getTiposIngressos(model);
        this.getIngressos(model);

        return "ingresso";
    }

    @RequestMapping(method= RequestMethod.POST)
    String cadastrar(@RequestParam Map<String,String> allRequestParams, Model model) {

        System.out.println(allRequestParams.toString());

        try {

            Ingresso ingresso = new Ingresso(IngressoTipoEnum.valueOf(allRequestParams.get("tipo")));
            ingresso.setEvento(eventoService.findOne(Integer.valueOf(allRequestParams.get("evento"))));

            ingressoService.save(ingresso);
            model.addAttribute("message","O ingresso  foi cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println(new Date(System.currentTimeMillis())+ " INFO --- " + e.getMessage());
            model.addAttribute("message","O ingresso é invalido!");
        }

        this.getEventos(model);
        this.getTiposIngressos(model);
        this.getIngressos(model);

        return "ingresso";
    }

    private void getTiposIngressos(Model model) {
        List<IngressoTipoEnum> ingressoTipoEnum = Arrays.asList(IngressoTipoEnum.values());
        model.addAttribute("tipos", ingressoTipoEnum);
    }

    private void getEventos(Model model) {
        List<Evento> eventos = eventoService.findAll();
        model.addAttribute("eventos", eventos);
    }

    private void getIngressos(Model model) {
        List<Ingresso> ingressos = ingressoService.findAll();
        model.addAttribute("ingressos", ingressos);
    }
}
