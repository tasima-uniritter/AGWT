package com.uniritter.agwt.eventos.domain.exception;

public class DataDoEventoNaoInformadoException extends Exception {

    @Override
    public String getMessage() {
        return "Data do evento não informado.";
    }
}
