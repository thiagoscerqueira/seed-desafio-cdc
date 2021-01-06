package br.com.jornadadev.casadocodigo.compra;

import lombok.ToString;
import org.modelmapper.internal.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@ToString(exclude = "compra")
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<ItemPedido> itens;

    @OneToOne
    private Compra compra;

    @Deprecated
    public Pedido() {
    }

    public Pedido(@NotNull
                  @Size(min = 1) List<ItemPedido> itens,
                  @NotNull Compra compra) {
        Assert.isTrue(!itens.isEmpty(), "todo pedido deve ter ao menos um item");
        Assert.isTrue(compra != null, "todo pedido deve ter uma compra");
        this.itens = itens;
        this.compra = compra;
    }

    public boolean totalIgual(@Positive @NotNull BigDecimal total) {
        return valorTotalPedido().compareTo(total) == 0;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public BigDecimal valorTotalPedido() {
        return itens.stream().map(ItemPedido::valorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
