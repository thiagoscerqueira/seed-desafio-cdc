package br.com.jornadadev.casadocodigo.cadastrocategorias;

import br.com.jornadadev.casadocodigo.core.UniqueValue;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class NovaCategoriaRequest {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "Nome duplicated")
    private String nome;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovaCategoriaRequest(@NotBlank String nome) {
        this.nome = nome;
    }

    public Categoria toModel() {
        return new Categoria(this.nome);
    }

    public String getNome() {
        return nome;
    }
}
