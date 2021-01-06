package br.com.jornadadev.casadocodigo.cadastrocategorias;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriasController {

    @PersistenceContext
    private final EntityManager entityManager;
    //private final CategoriaNomeDuplicadoValidator categoriaNomeDuplicadoValidator;

    /*@InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(categoriaNomeDuplicadoValidator);
    }*/

    @PostMapping
    @Transactional
    public void novaCategoria(@RequestBody @Valid NovaCategoriaRequest request) {
        Categoria novaCategoria = request.toModel();
        entityManager.persist(novaCategoria);
    }
}
