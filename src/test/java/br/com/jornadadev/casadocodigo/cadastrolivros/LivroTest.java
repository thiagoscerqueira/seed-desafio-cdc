package br.com.jornadadev.casadocodigo.cadastrolivros;

import br.com.jornadadev.casadocodigo.cadastroautores.Autor;
import br.com.jornadadev.casadocodigo.cadastrocategorias.Categoria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

class LivroTest {

    @Test
    @DisplayName("Deveria criar o livro quando passar um autor e uma categoria")
    public void deveCriarLivroQuandoPassaAutorECategoria() {
        Livro livro = new Livro("title", "resumo", "sumario", BigDecimal.TEN, 10, "isbn",
                LocalDate.now(), new Categoria("nome"), new Autor("nome", "email", "descricao"));

        Assertions.assertNotNull(livro, "Uma instância de livro deveria ter sido criada");
    }

    @Test
    @DisplayName("Não deveria criar o livro quando não passar um autor")
    public void naoDeveCriarLivroQuandoNaoPassaAutor() {
        Assertions.assertThrows(IllegalStateException.class, () ->
                new Livro("title", "resumo", "sumario", BigDecimal.TEN, 10, "isbn",
                        LocalDate.now(), new Categoria("nome"), null));
    }

    @Test
    @DisplayName("Não deveria criar o livro quando não passar uma categoria")
    public void naoDeveCriarLivroQuandoNaoPassaCategoria() {
        Assertions.assertThrows(IllegalStateException.class, () ->
                new Livro("title", "resumo", "sumario", BigDecimal.TEN, 10, "isbn",
                        LocalDate.now(), null, new Autor("nome", "email", "descricao")));
    }

}