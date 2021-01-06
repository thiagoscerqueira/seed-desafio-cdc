package br.com.jornadadev.casadocodigo.cadastroautores;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor
public class AutoresController {

    @PersistenceContext
    private EntityManager entityManager;

    /*private final AutorEmailValidator autorEmailValidator;

    @InitBinder
    public void init (WebDataBinder webDataBinder) {
        webDataBinder.addValidators(autorEmailValidator);
    }*/

    @PostMapping
    @Transactional
    public void novoAutor(@RequestBody @Valid NovoAutorRequest novoAutorRequest) {
        Autor autor = novoAutorRequest.toModel();
        entityManager.persist(autor);
    }
}
