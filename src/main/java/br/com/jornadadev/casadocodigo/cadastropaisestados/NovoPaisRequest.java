package br.com.jornadadev.casadocodigo.cadastropaisestados;

import br.com.jornadadev.casadocodigo.core.UniqueValue;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class NovoPaisRequest {

    @NotBlank
    @UniqueValue(domainClass = Pais.class, fieldName = "nome")
    private String nome;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovoPaisRequest(@NotBlank String nome) {
        this.nome = nome;
    }

    public Pais toModel() {
        return new Pais(nome);
    }
}
