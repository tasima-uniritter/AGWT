package com.uniritter.agwt.eventos.domain.exception;

public class DataMenorOuIgualAHojeException extends Exception {

    @Override
    public String getMessage() {
        return "A data do evento deve ser igual ou maior que a de hoje";
    }
}
