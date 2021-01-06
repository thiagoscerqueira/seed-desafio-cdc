package br.com.jornadadev.casadocodigo.compra;

import br.com.jornadadev.casadocodigo.cadastropaisestados.Estado;
import br.com.jornadadev.casadocodigo.cadastropaisestados.Pais;
import br.com.jornadadev.casadocodigo.core.ExisteId;
import br.com.jornadadev.casadocodigo.novocupom.Cupom;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.function.Function;

public class NovaCompraRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    private String documento;

    @NotBlank
    private String endereco;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cidade;

    @NotNull
    @ExisteId(domainClass = Pais.class, fieldName = "id")
    private Long idPais;

    @ExisteId(domainClass = Estado.class, fieldName = "id")
    private Long idEstado;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    @NotNull
    @Valid
    private PedidoRequest pedido;

    @ExisteId(domainClass = Cupom.class, fieldName = "codigo")
    private String codigoCupom;

    public NovaCompraRequest(@NotBlank @Email String email, @NotBlank String nome,
                             @NotBlank String sobrenome, @NotBlank String documento,
                             @NotBlank String endereco, @NotBlank String complemento,
                             @NotBlank String cidade, @NotNull Long idPais, Long idEstado,
                             @NotBlank String telefone, @NotBlank String cep,
                             @NotNull @Valid PedidoRequest pedido, String codigoCupom) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.idPais = idPais;
        this.idEstado = idEstado;
        this.telefone = telefone;
        this.cep = cep;
        this.pedido = pedido;
        this.codigoCupom = codigoCupom;
    }

    public Compra toModel(EntityManager entityManager, CupomRepository cupomRepository) {
        Pais pais = entityManager.find(Pais.class, idPais);
        Function<Compra, Pedido> construtorDePedido = pedido.toModel(entityManager);

        Compra compra = new Compra(email, nome, sobrenome, documento, endereco, complemento, cidade, pais, construtorDePedido);

        if (idEstado != null) {
            Estado estado = entityManager.find(Estado.class, idEstado);
            compra.setEstado(estado);
        }

        if (codigoCupom != null) {
            Cupom cupom = cupomRepository.getByCodigo(codigoCupom);
            compra.setCupom(cupom);
        }

        return compra;
    }

    public boolean documentoValido() {
        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);

        CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);

        return cpfValidator.isValid(this.documento, null) ||
                cnpjValidator.isValid(this.documento, null);
    }

    public String getDocumento() {
        return documento;
    }

    public Long getIdPais() {
        return idPais;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public String getCodigoCupom() {
        return codigoCupom;
    }
}
