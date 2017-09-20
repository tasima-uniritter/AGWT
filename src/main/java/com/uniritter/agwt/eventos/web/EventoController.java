package com.uniritter.agwt.eventos.web;

import com.uniritter.agwt.eventos.domain.Evento;
import com.uniritter.agwt.eventos.domain.enumeration.IngressoTipoEnum;
import com.uniritter.agwt.eventos.domain.exception.*;
import com.uniritter.agwt.eventos.dto.EventoDTO;
import com.uniritter.agwt.eventos.service.EventoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping(value="/evento")
public class EventoController {

    @Autowired
    private EventoService service;

    @Autowired
    private ModelMapper modelMapper;

    private EventoDTO convertToDto(Evento evento) {
        EventoDTO eventoDto = modelMapper.map(evento, EventoDTO.class);
        return eventoDto;
    }

    private Evento convertToEntity(EventoDTO eventoDto) throws ParseException {
        Evento evento = modelMapper.map(eventoDto, Evento.class);

        if (eventoDto.getId() > 0) {
            Evento oldEvento = service.findOne(eventoDto.getId());
            evento.setNome(oldEvento.getNome());
            evento.setDataDoEvento(oldEvento.getDataDoEvento());
            evento.setInicioVendas(oldEvento.getInicioVendas());
            evento.setFinalVendas(oldEvento.getFinalVendas());
            evento.setListaIngressoTipoEnum(oldEvento.getListaIngressoTipoEnum());
        }
        return evento;
    }

    @RequestMapping(value="/cadastro")
    public String cadastra(Model model) {

        this.getTiposIngressos(model);

        return "/evento/cadastro";
    }

    @RequestMapping(value="/cadastro", method= RequestMethod.POST)
    public String salva(@RequestParam Map<String,String> allRequestParams, Model model) {

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
            List<IngressoTipoEnum> ingressoTipoEnums = Arrays.asList(IngressoTipoEnum.values());
            List<IngressoTipoEnum> tiposForEvento = new ArrayList<IngressoTipoEnum>();

            for (IngressoTipoEnum tipo : ingressoTipoEnums) {

                if(allRequestParams.containsKey(tipo.getNome())){
                    tiposForEvento.add(tipo);
                }
            }

            Evento evento = new Evento(nome, dtDoEvento, dtInicio, dtFim, tiposForEvento);

            service.save(evento);

            model.addAttribute("message","O evento " + evento.getNome()+ " foi cadastrado com sucesso!");
        } catch (Exception e) {
            trataException(nome, model, e.getMessage());
        } catch (DataDoEventoNaoInformadoException e) {
            trataException(nome, model, e.getMessage());
        } catch (NomePermiteMax150CaracteresException e) {
            trataException(nome, model, e.getMessage());
        } catch (PeriodoVendaIngressosInvalidoException e) {
            trataException(nome, model, e.getMessage());
        } catch (NomeDoEventoNaoInformadoException e) {
            trataException(nome, model, e.getMessage());
        } catch (TipoIngressoDuplicadoException e) {
            trataException(nome, model, e.getMessage());
        } catch (DataMenorOuIgualAHojeException e) {
            trataException(nome, model, e.getMessage());
        }

        this.getTiposIngressos(model);

        return "/evento/cadastro";
    }

    private void trataException(String nome, Model model, String erro) {
        System.out.println(new Date(System.currentTimeMillis())+ " INFO --- " + erro);
        model.addAttribute("message", "O evento" + nome + " é invalido! Erro: "+ erro);
    }

    private void getTiposIngressos(Model model) {
        List<IngressoTipoEnum> ingressoTipoEnum = Arrays.asList(IngressoTipoEnum.values());
        model.addAttribute("tipos", ingressoTipoEnum);
    }

    @RequestMapping("/lista")
    public String listEventos(Model model){

        List<Evento> eventos = service.findAll();

        System.out.println(eventos);

        model.addAttribute("eventos", eventos);

        return "/evento/lista";
    }
}
