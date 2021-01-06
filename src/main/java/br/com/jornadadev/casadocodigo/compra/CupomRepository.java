package br.com.jornadadev.casadocodigo.compra;

import br.com.jornadadev.casadocodigo.novocupom.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Long> {
    Cupom getByCodigo(String codigoCupom);
}
