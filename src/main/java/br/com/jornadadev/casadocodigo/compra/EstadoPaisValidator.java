package br.com.jornadadev.casadocodigo.compra;

import br.com.jornadadev.casadocodigo.cadastropaisestados.Estado;
import br.com.jornadadev.casadocodigo.cadastropaisestados.Pais;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class EstadoPaisValidator implements Validator {

    private final EntityManager entityManager;

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

        Pais pais = entityManager.find(Pais.class, novaCompraRequest.getIdPais());

        if (!pais.temEstados()) {
            return;
        }

        if (novaCompraRequest.getIdEstado() == null) {
            errors.rejectValue("idEstado", null, "País informado possui Estados. É necessário informar um Estado");
            return;
        }

        Estado estado = entityManager.find(Estado.class, novaCompraRequest.getIdEstado());

        if (!estado.pertenceAoPais(pais)) {
            errors.rejectValue("idEstado", null, "Estado não pertence ao pais");
        }
    }
}
