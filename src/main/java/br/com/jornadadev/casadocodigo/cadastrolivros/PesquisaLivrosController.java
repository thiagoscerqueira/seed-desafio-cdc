package br.com.jornadadev.casadocodigo.cadastrolivros;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class PesquisaLivrosController {

    private final LivroRepository livroRepository;

    @GetMapping
    public List<TodosLivrosResponse> listaTodosLivros() {
        return livroRepository.listaIdNomeTodosLivros()
                .stream().map(livro -> new TodosLivrosResponse(livro.getId(), livro.getTitulo()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{idLivro}")
    public ResponseEntity<LivroDetalheResponse> listaLivroDetalhe(@PathVariable Long idLivro) {
        Optional<Livro> livroPesquisa = livroRepository.findById(idLivro);
        if (!livroPesquisa.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Livro livro = livroPesquisa.get();
        LivroDetalheResponse livroDetalheResponse = new LivroDetalheResponse(livro.getTitulo(), livro.getResumo(),
                livro.getSumario(), livro.getPreco(), livro.getNumeroPaginas(), livro.getIsbn(),
                livro.getAutor().getNome(), livro.getAutor().getDescricao());
        return ResponseEntity.ok(livroDetalheResponse);
    }
}
