package br.com.jornadadev.casadocodigo.cadastrolivros;

import br.com.jornadadev.casadocodigo.core.ExisteId;
import br.com.jornadadev.casadocodigo.core.UniqueValue;
import br.com.jornadadev.casadocodigo.cadastrocategorias.Categoria;
import br.com.jornadadev.casadocodigo.cadastroautores.Autor;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@ToString
public class NovoLivroRequest {

    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "titulo", message = "Título do livro deve ser único")
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String resumo;

    private String sumario;

    @NotNull
    @Min(value = 20)
    private BigDecimal preco;

    @NotNull
    @Min(value = 100)
    private Integer numeroPaginas;

    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "isbn", message = "Isbn do livro deve ser único")
    private String isbn;

    @NotNull
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate dataPublicacao;

    @NotNull
    @ExisteId(fieldName = "id", domainClass = Categoria.class)
    private Long idCategoria;

    @NotNull
    @ExisteId(fieldName = "id", domainClass = Autor.class)
    private Long idAutor;

    public NovoLivroRequest(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, String sumario,
                            @NotNull @DecimalMin(value = "20.0") BigDecimal preco,
                            @NotNull @Min(value = 100) Integer numeroPaginas, @NotBlank String isbn,
                            @Future LocalDate dataPublicacao,
                            @NotNull Long idCategoria, @NotNull Long idAutor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.idCategoria = idCategoria;
        this.idAutor = idAutor;
    }

    public Livro toModel(EntityManager entityManager) {
        Autor autor = entityManager.find(Autor.class, idAutor);
        Categoria categoria = entityManager.find(Categoria.class, idCategoria);
        return new Livro(titulo, resumo, sumario, preco, numeroPaginas, isbn, dataPublicacao,
                categoria, autor);
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public Long getIdAutor() {
        return idAutor;
    }
}
