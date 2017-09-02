package com.uniritter.agwt.eventos.repository;

import com.uniritter.agwt.eventos.domain.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento,Long> {

}
