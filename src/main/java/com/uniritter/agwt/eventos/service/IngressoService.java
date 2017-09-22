package com.uniritter.agwt.eventos.service;

import com.uniritter.agwt.eventos.domain.Ingresso;
import com.uniritter.agwt.eventos.repository.IngressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngressoService {

    @Autowired
    private IngressoRepository repository;

    public Ingresso save(Ingresso ingresso) {
        return repository.save(ingresso);
    }

    public List<Ingresso> findAll(){
        return repository.findAll();
    }

    public Ingresso findOne(long id) {
        return repository.findOne(id);
    }
}
