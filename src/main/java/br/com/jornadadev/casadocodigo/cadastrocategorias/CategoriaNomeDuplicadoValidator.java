package br.com.jornadadev.casadocodigo.cadastrocategorias;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class CategoriaNomeDuplicadoValidator implements Validator {

    private final CategoriaRepository categoriaRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return NovaCategoriaRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        NovaCategoriaRequest novaCategoriaRequest = (NovaCategoriaRequest) target;

        if (categoriaRepository.existsByNomeIgnoreCase(novaCategoriaRequest.getNome())) {
            errors.rejectValue("nome", null,"Já existe categoria com este nome");
        }
    }
}
