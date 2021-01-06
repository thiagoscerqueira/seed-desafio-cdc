package br.com.jornadadev.casadocodigo.compra;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CpfCnpjValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return NovaCompraRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        NovaCompraRequest novaCompraRequest = (NovaCompraRequest) target;

        if (!novaCompraRequest.documentoValido()) {
            errors.rejectValue("documento", null, "Documento inválido");
        }
    }
}
