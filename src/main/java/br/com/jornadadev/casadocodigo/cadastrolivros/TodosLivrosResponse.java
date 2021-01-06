package br.com.jornadadev.casadocodigo.cadastrolivros;

import lombok.Getter;

@Getter
public class TodosLivrosResponse {

    private Long id;
    private String titulo;

    public TodosLivrosResponse(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }
}
