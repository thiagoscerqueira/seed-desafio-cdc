package br.com.jornadadev.casadocodigo.compra;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class DetalheCompraController {

    private final EntityManager entityManager;

    @GetMapping("/{id}")
    public ResponseEntity<DetalheCompraResponse> detalheCompra(@PathVariable Long id) {
        Compra compra = entityManager.find(Compra.class, id);

        if (compra == null) {
            return ResponseEntity.notFound().build();
        }
        List<DetalheItemPedidoResponse> listaItemDetalhe = compra.getPedido().getItens().stream().map(item ->
                new DetalheItemPedidoResponse(item.getLivro().getTitulo(), item.getQuantidade(), item.getValorLivro()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new DetalheCompraResponse(listaItemDetalhe, compra.valorOriginalPedido(),
                compra.temCupom(), compra.valorComCupomAplicado()));
    }
}
