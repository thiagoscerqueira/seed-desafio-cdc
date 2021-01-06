package br.com.jornadadev.casadocodigo.compra;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class DetalheCompraResponse {

    private List<DetalheItemPedidoResponse> itens;
    private BigDecimal valorOriginal;
    private boolean temCupom;
    private BigDecimal valorComCupomAplicado;

    public DetalheCompraResponse(List<DetalheItemPedidoResponse> itens, BigDecimal valorOriginal, boolean temCupom, BigDecimal valorComCupomAplicado) {
        this.itens = itens;
        this.valorOriginal = valorOriginal;
        this.temCupom = temCupom;
        this.valorComCupomAplicado = valorComCupomAplicado;
    }
}
