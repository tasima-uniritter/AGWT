package com.uniritter.agwt.eventos.web;

import com.uniritter.agwt.eventos.domain.Evento;
import com.uniritter.agwt.eventos.domain.enumeration.IngressoTipoEnum;
import com.uniritter.agwt.eventos.domain.exception.*;
import com.uniritter.agwt.eventos.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping(value="/evento")
public class EventoController {

    @Autowired
    private EventoService service;

    @RequestMapping
    public String cadastra(Model model) {

        this.getTiposIngressos(model);
        this.getEventos(model);
        model.addAttribute("evento", new Evento());

        return "evento";
    }

    @RequestMapping(method= RequestMethod.POST)
    public String salva(@RequestParam Map<String,String> allRequestParams, Model model) {

        Evento evento = new Evento();
        model.addAttribute("evento",evento);

        String nome = allRequestParams.get("nome");

        try {

            String[] partsEvento = allRequestParams.get("dataDoEvento").split("-");
            LocalDate dtDoEvento = LocalDate.of(Integer.valueOf(partsEvento[0]),
                                                Integer.valueOf(partsEvento[1]),
                                                Integer.valueOf(partsEvento[2]));

            String[] partsInicio = allRequestParams.get("inicioVendas").split("-");
            LocalDate dtInicio = LocalDate.of(Integer.valueOf(partsInicio[0]),
                                                Integer.valueOf(partsInicio[1]),
                                                Integer.valueOf(partsInicio[2]));

            String[] partsFim = allRequestParams.get("finalVendas").split("-");
            LocalDate dtFim = LocalDate.of(Integer.valueOf(partsFim[0]),
                                                Integer.valueOf(partsFim[1]),
                                                Integer.valueOf(partsFim[2]));

            // retorno do enun
            List<IngressoTipoEnum> tiposIngresos = Arrays.asList(IngressoTipoEnum.values());
            List<IngressoTipoEnum> tiposIngressoEvento = new ArrayList<IngressoTipoEnum>();

            for (IngressoTipoEnum tipo : tiposIngresos) {

                if(allRequestParams.containsKey(tipo.getNome())){
                    tiposIngressoEvento.add(tipo);
                }
            }

            evento = new Evento(nome, dtDoEvento, dtInicio, dtFim, tiposIngressoEvento);

            service.save(evento);

            model.addAttribute("message","O evento " + evento.getNome()+ " foi cadastrado com sucesso!");
        } catch (DataDoEventoNaoInformadoException e) {
            trataException(evento, model, e.getMessage());
        } catch (NomePermiteMax150CaracteresException e) {
            trataException(evento, model, e.getMessage());
        } catch (PeriodoVendaIngressosInvalidoException e) {
            trataException(evento, model, e.getMessage());
        } catch (NomeDoEventoNaoInformadoException e) {
            trataException(evento, model, e.getMessage());
        } catch (TipoIngressoDuplicadoException e) {
            trataException(evento, model, e.getMessage());
        } catch (DataMenorOuIgualAHojeException e) {
            trataException(evento, model, e.getMessage());
        }

        this.getTiposIngressos(model);
        this.getEventos(model);

        return "evento";
    }

    private void trataException(Evento evento, Model model, String erro) {
        System.out.println(new Date(System.currentTimeMillis())+ " INFO --- " + erro);
        model.addAttribute("message", erro);
        model.addAttribute("erro", "sim");
        model.addAttribute("evento",evento);
    }

    private void getTiposIngressos(Model model) {
        List<IngressoTipoEnum> ingressoTipoEnum = Arrays.asList(IngressoTipoEnum.values());
        model.addAttribute("tipos", ingressoTipoEnum);
    }

    private void getEventos(Model model) {
        List<Evento> eventos = service.findAll();
        model.addAttribute("eventos", eventos);
    }
}
