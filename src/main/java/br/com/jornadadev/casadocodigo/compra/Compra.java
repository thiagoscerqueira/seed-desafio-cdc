package br.com.jornadadev.casadocodigo.compra;

import br.com.jornadadev.casadocodigo.cadastropaisestados.Estado;
import br.com.jornadadev.casadocodigo.cadastropaisestados.Pais;
import br.com.jornadadev.casadocodigo.novocupom.Cupom;
import lombok.ToString;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Function;

@Entity
@ToString
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String nome;
    private String sobrenome;
    private String documento;
    private String endereco;
    private String complemento;
    private String cidade;

    @ManyToOne
    private Pais pais;

    @ManyToOne
    private Estado estado;

    @OneToOne(mappedBy = "compra", cascade = CascadeType.PERSIST)
    private Pedido pedido;

    @ManyToOne
    private Cupom cupom;

    @Deprecated
    public Compra() {
    }

    public Compra(String email, String nome, String sobrenome, String documento, String endereco, String complemento,
                  String cidade, Pais pais, Function<Compra, Pedido> construtorDePedido) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.pais = pais;
        this.pedido = construtorDePedido.apply(this);
    }

    public void setEstado(Estado estado) {
        Assert.isTrue(estado.pertenceAoPais(pais), "Estado não percence ao pais");
        this.estado = estado;
    }

    public void setCupom(Cupom cupom) {
        Assert.notNull(cupom, "Não pode tentar associar um cupom vazio");
        Assert.isNull(this.cupom, "Não é possível alterar um compom de uma compra");
        Assert.isTrue(cupom.isValido(), "Não é permitido associar um cupom vencido");
        this.cupom = cupom;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public BigDecimal valorOriginalPedido() {
        return pedido.valorTotalPedido();
    }

    public boolean temCupom () {
        return this.cupom != null;
    }

    public BigDecimal valorComCupomAplicado() {
        if (this.cupom == null) {
            return null;
        }

        BigDecimal valorOriginal = valorOriginalPedido();
        BigDecimal valorDesconto = valorOriginal.multiply(this.cupom.getPercentual()).divide(new BigDecimal(100),
                RoundingMode.HALF_UP);
        return valorOriginal.subtract(valorDesconto);
    }
}
