package com.uniritter.agwt.eventos.domain.exception;

public class TipoIngressoDuplicadoException extends Throwable {

    @Override
    public String getMessage() {
        return "Tipo do ingresso duplicado.";
    }
}
