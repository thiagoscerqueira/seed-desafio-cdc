package br.com.jornadadev.casadocodigo.cadastroautores;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class AutorEmailValidator implements Validator {

    private final AutorRepository autorRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return NovoAutorRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        NovoAutorRequest novoAutorRequest = (NovoAutorRequest) target;

        if (autorRepository.existsByEmailIgnoreCase(novoAutorRequest.getEmail())) {
            errors.rejectValue("email", null, "E-mail do autor não pode ser duplicado");
        }
    }
}
