package com.lucasengcomp.ecommerce.model;

import com.lucasengcomp.ecommerce.model.enums.SexoCliente;
import com.lucasengcomp.ecommerce.pk.EntidadeBaseInteger;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Getter
@Setter
@Entity
@NamedStoredProcedureQuery(name = "compraram_acima_media", procedureName = "compraram_acima_media",
        resultClasses = Cliente.class,
        parameters = {
                @StoredProcedureParameter(name = "ano", type = Integer.class, mode = ParameterMode.IN)
        })
@Table(name = "cliente", uniqueConstraints = {@UniqueConstraint(name = "unq_cpf", columnNames = {"cpf"})},
        indexes = {@Index(name = "idx_nome", columnList = "nome")})
@SecondaryTable(name = "cliente_detalhe", pkJoinColumns = @PrimaryKeyJoinColumn(name = "cliente_id"),
        foreignKey = @ForeignKey(name = "fk_cliente_detalhe_cliente"))
public class Cliente extends EntidadeBaseInteger {

    @NotBlank
    @Column(length = 100, nullable = false)
    private String nome;

    @NotBlank
    @Pattern(regexp = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})|(\\d{11})$")
    @Column(length = 14, nullable = false)
    private String cpf;

    @Transient
    private String primeiroNome;

    @NotNull
    @Column(table = "cliente_detalhe", name = "sexo", length = 100)
    @Enumerated(EnumType.STRING)
    private SexoCliente sexoCliente;

    @Column(name = "data_nascimento", table = "cliente_detalhe")
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    @NotEmpty
    @ElementCollection
    @CollectionTable(name = "cliente_contato",
            joinColumns = @JoinColumn(name = "cliente_id"))
    @MapKeyColumn(name = "tipo")
    @Column(name = "descricao")
    private Map<String, String> contatos;

    @PostLoad
    public void configurarPrimeiroNome() {
        if (nome != null && !nome.isBlank()) {
            int index = nome.indexOf(" ");
            if (index > -1) {
                primeiroNome = nome.substring(0, index);
            }
        }
    }
}
