package com.uniritter.agwt.eventos.domain.exception;

public class DataDoEventoNaoInformadoException extends Throwable {

    @Override
    public String getMessage() {
        return "Data do evento não informado.";
    }
}
