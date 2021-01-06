package br.com.jornadadev.casadocodigo.compra;

import br.com.jornadadev.casadocodigo.cadastrolivros.Livro;
import lombok.Getter;
import lombok.ToString;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@ToString
@Embeddable
@Getter
public class ItemPedido {

    @ManyToOne
    private @NotNull Livro livro;

    private @Positive Integer quantidade;
    private @Positive BigDecimal valorLivro;

    @Deprecated
    public ItemPedido() {
    }

    public ItemPedido(@NotNull Livro livro, @Positive Integer quantidade) {
        this.livro = livro;
        this.quantidade = quantidade;
        this.valorLivro = livro.getPreco();
    }

    public BigDecimal valorTotal() {
        return this.valorLivro.multiply(new BigDecimal(this.quantidade));
    }


}
