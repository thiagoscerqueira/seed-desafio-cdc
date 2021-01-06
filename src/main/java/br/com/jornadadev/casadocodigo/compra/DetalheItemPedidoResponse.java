package br.com.jornadadev.casadocodigo.compra;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class DetalheItemPedidoResponse {

    private String tituloLivro;
    private Integer quantidade;
    private BigDecimal valor;

    public DetalheItemPedidoResponse(String tituloLivro, Integer quantidade, BigDecimal valor) {
        this.tituloLivro = tituloLivro;
        this.quantidade = quantidade;
        this.valor = valor;
    }


}
