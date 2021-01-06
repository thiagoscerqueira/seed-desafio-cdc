package br.com.jornadadev.casadocodigo.cadastrolivros;

import br.com.jornadadev.casadocodigo.cadastroautores.Autor;
import br.com.jornadadev.casadocodigo.cadastrocategorias.Categoria;
import lombok.Getter;
import lombok.ToString;
import org.modelmapper.internal.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@ToString
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String resumo;
    private String sumario;
    private BigDecimal preco;
    private Integer numeroPaginas;
    private String isbn;
    private LocalDate dataPublicacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAutor")
    private Autor autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;

    @Deprecated
    public Livro() {
    }

    public Livro(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public Livro(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, String sumario,
                 @NotNull @DecimalMin(value = "20.0") BigDecimal preco,
                 @NotNull @Min(value = 100) Integer numeroPaginas, @NotBlank String isbn,
                 @Future LocalDate dataPublicacao,
                 @NotNull Categoria categoria, @NotNull Autor autor) {
        Assert.state(categoria != null, "Para criar um livro, é obrigatório passar uma categoria");
        Assert.state(autor != null, "Para criar um livro, é necessário passar um autor");
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}
