package br.com.jornadadev.casadocodigo.cadastroautores;

import br.com.jornadadev.casadocodigo.core.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NovoAutorRequest {

    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    @NotBlank(message = "E-mail não pode ser vazio")
    @Email(message = "E-mail com formato inválido")
    @UniqueValue(domainClass = Autor.class, fieldName = "email", message = "E-mail duplicated")
    private String email;

    @NotBlank(message = "Descrição não pode ser vazia")
    @Size(max = 400, message = "Descrição não deve ter mais de 400 caracteres")
    private String descricao;

    public NovoAutorRequest(@NotBlank String nome,
                            @NotBlank @Email String email,
                            @NotBlank
                        @Size(max = 400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public Autor toModel() {
        return new Autor(nome, email, descricao);
    }

    public String getEmail() {
        return email;
    }
}
