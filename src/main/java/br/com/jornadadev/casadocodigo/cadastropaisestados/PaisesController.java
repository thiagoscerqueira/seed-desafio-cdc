package br.com.jornadadev.casadocodigo.cadastropaisestados;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/paises")
public class PaisesController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public String novoPais(@RequestBody @Valid NovoPaisRequest request) {
        Pais pais = request.toModel();
        entityManager.persist(pais);
        return pais.toString();
    }
}
