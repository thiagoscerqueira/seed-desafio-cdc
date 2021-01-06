package br.com.jornadadev.casadocodigo.cadastroautores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    boolean existsByEmailIgnoreCase(String email);
}
