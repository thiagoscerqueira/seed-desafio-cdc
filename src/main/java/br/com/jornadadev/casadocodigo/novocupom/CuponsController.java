package br.com.jornadadev.casadocodigo.novocupom;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/cupons")
public class CuponsController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public String novoCupom(@RequestBody @Valid NovoCupomRequest novoCupomRequest) {
        Cupom cupom = novoCupomRequest.toModel();
        entityManager.persist(cupom);
        return cupom.toString();
    }
}
