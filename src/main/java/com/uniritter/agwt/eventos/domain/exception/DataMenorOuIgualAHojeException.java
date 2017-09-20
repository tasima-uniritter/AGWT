package com.uniritter.agwt.eventos.domain.exception;

public class DataMenorOuIgualAHojeException extends Throwable {

    @Override
    public String getMessage() {
        return "Data menor ou igual a hoje.";
    }
}
