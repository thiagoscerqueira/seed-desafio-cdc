package br.com.jornadadev.casadocodigo.core;

import lombok.Getter;

@Getter
public class FieldErrorOutputDto {
    private String field;
    private String error;

    public FieldErrorOutputDto(String field, String error) {
        this.field = field;
        this.error = error;
    }


}
