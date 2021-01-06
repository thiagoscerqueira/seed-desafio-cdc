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
@RequestMapping("/estados")
public class EstadosController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public String novoEstado(@RequestBody @Valid NovoEstadoRequest request) {
        Estado estado = request.toModel(entityManager);
        entityManager.persist(estado);
        return estado.toString();
    }
}
