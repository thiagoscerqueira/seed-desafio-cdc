package br.com.jornadadev.casadocodigo.core;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationErrorDto {

    private List<String> globalListErrors = new ArrayList<>();
    private List<FieldErrorOutputDto> errors = new ArrayList<>();

    public void addGlobalError(String error) {
        this.globalListErrors.add(error);
    }

    public void addFieldError(FieldErrorOutputDto error) {
        this.errors.add(error);
    }

}
