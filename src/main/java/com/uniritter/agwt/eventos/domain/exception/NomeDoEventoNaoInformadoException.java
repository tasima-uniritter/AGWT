package com.uniritter.agwt.eventos.domain.exception;

public class NomeDoEventoNaoInformadoException extends Throwable {

    @Override
    public String getMessage() {
        return "Nome do evento não informado.";
    }
}
