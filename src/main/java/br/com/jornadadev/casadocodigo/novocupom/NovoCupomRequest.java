package br.com.jornadadev.casadocodigo.novocupom;

import br.com.jornadadev.casadocodigo.core.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NovoCupomRequest {

    @NotEmpty
    @UniqueValue(domainClass = Cupom.class, fieldName = "codigo")
    private String codigo;

    @NotNull
    @Positive
    @Max(100)
    private BigDecimal percentual;

    @NotNull
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate validade;

    public NovoCupomRequest(@NotEmpty String codigo, @NotNull @Positive @Max(100) BigDecimal percentual, @NotNull @Future LocalDate validade) {
        this.codigo = codigo;
        this.percentual = percentual;
        this.validade = validade;
    }

    public Cupom toModel() {
        return new Cupom(codigo, percentual, validade);
    }
}
