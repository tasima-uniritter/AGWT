package com.uniritter.agwt.eventos.repository;

import com.uniritter.agwt.eventos.domain.Ingresso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngressoRepository extends JpaRepository<Ingresso,Long> {

}
