package br.com.jornadadev.casadocodigo.novocupom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.time.LocalDate;

class CupomTest {

    @ParameterizedTest
    @CsvSource({
            "0, true",
            "-1, false",
            "1, true",
            "-10,false",
            "20,true"
    })
    public void testaValidadeCupom(Long quantidadeDiasSomaDataHoje, boolean resultadoEsperado) {
        Cupom cupom = new Cupom("xxx", BigDecimal.ONE, LocalDate.now().plusDays(quantidadeDiasSomaDataHoje));
        Assertions.assertEquals(resultadoEsperado, cupom.isValido());
    }

}