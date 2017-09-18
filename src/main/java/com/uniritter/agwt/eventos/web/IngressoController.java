package com.uniritter.agwt.eventos.web;

import com.uniritter.agwt.eventos.domain.Evento;
import com.uniritter.agwt.eventos.domain.Ingresso;
import com.uniritter.agwt.eventos.domain.enumeration.IngressoTipoEnum;
import com.uniritter.agwt.eventos.repository.EventoRepository;
import com.uniritter.agwt.eventos.repository.IngressoRepository;
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
@RequestMapping(value="/ingresso")
public class IngressoController {

    @Autowired
    private IngressoRepository repository;

    @Autowired
    private EventoRepository repositoryEventos;

    @RequestMapping(value="/cadastro")
    String cadastra(Model model) {

        List<Evento> eventos = repositoryEventos.findAll();

        System.out.println(eventos);

        model.addAttribute("eventos", eventos);

        return "/ingresso/cadastro";
    }

    @RequestMapping(value="/cadastro/salva", method= RequestMethod.POST)
    String salva(
                 @RequestParam String idEvento,
                 @RequestParam IngressoTipoEnum tipo,
                 @RequestParam String valor,
                 @RequestParam String inicioVendas,
                 @RequestParam String finalVendas,
                 Model model) {

        System.out.println(idEvento);
        System.out.println(tipo);
        System.out.println(valor);
        System.out.println(inicioVendas);
        System.out.println(finalVendas);

        try {
            String[] pI = inicioVendas.split("-");
            LocalDate dataInicioVendas = LocalDate.of(Integer.valueOf(pI[0]),
                    Integer.valueOf(pI[1]),Integer.valueOf(pI[2]));

            String[] pF = finalVendas.split("-");
            LocalDate dataFinalVendas = LocalDate.of(Integer.valueOf(pF[0]),
                    Integer.valueOf(pF[1]),Integer.valueOf(pF[2]));

            //Ingresso ingresso = new Ingresso();


            //repository.save(evento);
            model.addAttribute("message","O ingresos  foi cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println(new Date(System.currentTimeMillis())+ " INFO --- " + e.getMessage());
            model.addAttribute("message","O ingresso é invalido!");
        }

        return "/ingresso/cadastro";
    }

    @RequestMapping("/lista")
    public String listIngressos(Model model){

        List<Ingresso> ingressos = repository.findAll();

        System.out.println(ingressos);

        model.addAttribute("ingressos", ingressos);

        return "/ingresso/lista";
    }
}
