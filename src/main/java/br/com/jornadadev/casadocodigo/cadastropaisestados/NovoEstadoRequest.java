package br.com.jornadadev.casadocodigo.cadastropaisestados;

import br.com.jornadadev.casadocodigo.core.ExisteId;
import br.com.jornadadev.casadocodigo.core.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovoEstadoRequest {

    @NotBlank
    @UniqueValue(domainClass = Estado.class, fieldName = "nome")
    private String nome;

    @NotNull
    @ExisteId(fieldName = "id", domainClass = Pais.class)
    private Long idPais;

    public NovoEstadoRequest(@NotBlank String nome, @NotNull Long idPais) {
        this.nome = nome;
        this.idPais = idPais;
    }

    public Estado toModel(EntityManager entityManager) {
        Pais pais = entityManager.find(Pais.class, idPais);
        return new Estado(nome, pais);
    }
}
