package br.com.jornadadev.casadocodigo.cadastrolivros;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivrosController {

    private final EntityManager entityManager;

    @PostMapping
    @Transactional
    public String novoLivro(@RequestBody @Valid NovoLivroRequest novoLivroRequest) {
        Livro livro = novoLivroRequest.toModel(entityManager);
        entityManager.persist(livro);
        return novoLivroRequest.toString();
    }


}
