package com.uniritter.agwt.eventos.domain.exception;

public class PeriodoVendaIngressosInvalidoException extends Throwable {
    public PeriodoVendaIngressosInvalidoException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Periodo de venda do ingresso é invalido.";
    }
}
