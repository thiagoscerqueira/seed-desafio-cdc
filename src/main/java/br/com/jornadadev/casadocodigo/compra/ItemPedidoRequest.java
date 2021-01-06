package br.com.jornadadev.casadocodigo.compra;

import br.com.jornadadev.casadocodigo.cadastrolivros.Livro;
import br.com.jornadadev.casadocodigo.core.ExisteId;

import javax.persistence.EntityManager;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ItemPedidoRequest {

    @NotNull
    @ExisteId(domainClass = Livro.class, fieldName = "id")
    private Long idLivro;

    @NotNull
    @Min(value = 1)
    private Integer quantidade;

    public ItemPedidoRequest(@NotNull Long idLivro, @NotNull @Size(min = 1) Integer quantidade) {
        this.idLivro = idLivro;
        this.quantidade = quantidade;
    }

    public Long getIdLivro() {
        return idLivro;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public ItemPedido toModel(EntityManager entityManager) {
        Livro livro = entityManager.find(Livro.class, idLivro);
        return new ItemPedido(livro, quantidade);
    }
}
