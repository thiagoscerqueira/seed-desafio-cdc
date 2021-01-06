package br.com.jornadadev.casadocodigo.cadastrolivros;

import br.com.jornadadev.casadocodigo.cadastroautores.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class AutorExisteValidator implements Validator {

    private final AutorRepository autorRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return NovoLivroRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        NovoLivroRequest novoLivroRequest = (NovoLivroRequest) target;
        if (!autorRepository.existsById(novoLivroRequest.getIdAutor())) {
            errors.rejectValue("idAutor", null, "Autor informado não existe");
        }
    }
}
