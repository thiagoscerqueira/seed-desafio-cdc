package br.com.jornadadev.casadocodigo.compra;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class ComprasController {

    private final EntityManager entityManager;
    private final EstadoPaisValidator estadoPaisValidator;
    private final CupomValidator cupomValidator;
    private final CupomRepository cupomRepository;

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new CpfCnpjValidator(), estadoPaisValidator, cupomValidator);
    }

    @PostMapping
    @Transactional
    public String novoPagamentoDadosParciais(@RequestBody @Valid NovaCompraRequest request) {
        Compra compra = request.toModel(entityManager, cupomRepository);
        entityManager.persist(compra);
        return compra.toString();
    }
}
