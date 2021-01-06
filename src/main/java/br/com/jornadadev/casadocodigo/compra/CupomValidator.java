package br.com.jornadadev.casadocodigo.compra;

import br.com.jornadadev.casadocodigo.novocupom.Cupom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class CupomValidator implements Validator {

    private final CupomRepository cupomRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return NovaCompraRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NovaCompraRequest novaCompraRequest = (NovaCompraRequest) target;

        if (novaCompraRequest.getCodigoCupom() == null) {
            return;
        }

        Cupom cupom = cupomRepository.getByCodigo(novaCompraRequest.getCodigoCupom());

        if (!cupom.isValido()) {
            errors.rejectValue("codigoCupom", null, "Cupom informado está vencido");
        }
    }
}
