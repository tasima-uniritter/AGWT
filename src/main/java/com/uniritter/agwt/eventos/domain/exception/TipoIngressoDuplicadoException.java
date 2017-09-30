package com.uniritter.agwt.eventos.domain.exception;

public class TipoIngressoDuplicadoException extends Exception {

    @Override
    public String getMessage() {
        return "Tipo do ingresso duplicado.";
    }
}
