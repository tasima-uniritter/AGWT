package com.uniritter.agwt.eventos.domain.exception;

public class NomePermiteMax150CaracteresException extends Throwable {

    @Override
    public String getMessage() {
        return "Nome permite no máximo 150 caracteres.";
    }
}
