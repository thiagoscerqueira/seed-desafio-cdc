package br.com.jornadadev.casadocodigo.cadastropaisestados;

import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    private Pais pais;

    @Deprecated
    public Estado() {
    }

    public Estado(String nome, Pais pais) {
        this.nome = nome;
        this.pais = pais;
    }

    public boolean pertenceAoPais(Pais pais) {
        return this.pais.equals(pais);
    }
}
