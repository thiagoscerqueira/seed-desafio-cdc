package br.com.jornadadev.casadocodigo.cadastrolivros;

import br.com.jornadadev.casadocodigo.cadastrocategorias.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class CategoriaExisteValidator implements Validator {

    private final CategoriaRepository categoriaRepository;

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
        if (!categoriaRepository.existsById(novoLivroRequest.getIdCategoria())) {
            errors.rejectValue("idCategoria", null, "Categoria informada não existe");
        }
    }
}
