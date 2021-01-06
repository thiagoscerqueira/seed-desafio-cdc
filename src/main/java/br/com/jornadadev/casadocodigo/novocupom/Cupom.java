package br.com.jornadadev.casadocodigo.novocupom;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@ToString
public class Cupom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    private BigDecimal percentual;
    private LocalDate validade;

    @Deprecated
    public Cupom() {
    }

    public Cupom(@NotEmpty String codigo, @NotNull @Positive @Max(100) BigDecimal percentual, @NotNull
        @Future LocalDate validade) {
        this.codigo = codigo;
        this.percentual = percentual;
        this.validade = validade;
    }

    public boolean isValido() {
        LocalDate dataAtual = LocalDate.now();
        return dataAtual.compareTo(validade) <= 0;
    }

    public BigDecimal getPercentual() {
        return percentual;
    }
}
