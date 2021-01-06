package br.com.jornadadev.casadocodigo.compra;

import org.modelmapper.internal.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PedidoRequest {

    @NotNull
    @Positive
    private BigDecimal total;

    @NotNull
    @Size(min = 1, message = "Pedido deve ter ao menos um item")
    @Valid
    private List<ItemPedidoRequest> itens;

    public PedidoRequest(@NotNull @Positive BigDecimal total,
                         @NotNull @Size(min = 1, message = "Pedido deve ter ao menos um item") @Valid List<ItemPedidoRequest> itens) {
        this.total = total;
        this.itens = itens;
    }

    public Function<Compra, Pedido> toModel(EntityManager entityManager) {
        List<ItemPedido> listaItensPedido = itens.stream().map(item -> item.toModel(entityManager))
                .collect(Collectors.toList());

        return (compra) -> {
            Pedido pedido = new Pedido(listaItensPedido, compra);
            Assert.isTrue(pedido.totalIgual(this.total), "Valor total do pedido inválido");
            return pedido;
        };
    }

    public List<ItemPedidoRequest> getItens() {
        return itens;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
