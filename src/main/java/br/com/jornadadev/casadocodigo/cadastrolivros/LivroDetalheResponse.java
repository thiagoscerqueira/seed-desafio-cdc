package br.com.jornadadev.casadocodigo.cadastrolivros;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class LivroDetalheResponse {

    private String titulo;
    private String resumo;
    private String sumario;
    private BigDecimal preco;
    private Integer numeroPaginas;
    private String isbn;
    private String nomeAutor;
    private String descricaoAutor;
}
