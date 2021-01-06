package br.com.jornadadev.casadocodigo.cadastrolivros;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("select l.id as id, l.titulo as titulo from Livro l")
    List<Livro> listaIdNomeTodosLivros();
}
