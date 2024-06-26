package com.lucasengcomp.ecommerce.model;

import com.lucasengcomp.ecommerce.converter.BooleanToSimNaoConverter;
import com.lucasengcomp.ecommerce.dto.ProdutoDTO;
import com.lucasengcomp.ecommerce.embeddables.Atributo;
import com.lucasengcomp.ecommerce.listener.GenericoListener;
import com.lucasengcomp.ecommerce.pk.EntidadeBaseInteger;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NamedNativeQueries({
        @NamedNativeQuery(name = "produto_loja.listar",
                query = "select id, nome, descricao, data_criacao, data_ultima_atualizacao, preco, foto " +
                        " from produto_loja", resultClass = Produto.class),
        @NamedNativeQuery(name = "ecm_produto.listar",
                query = "select * from ecm_produto", resultSetMapping = "ecm_produto.Produto")
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "produto_loja.Produto",
                entities = {@EntityResult(entityClass = Produto.class)}),
        @SqlResultSetMapping(name = "ecm_produto.Produto",
                entities = {@EntityResult(entityClass = Produto.class,
                        fields = {
                                @FieldResult(name = "id", column = "prd_id"),
                                @FieldResult(name = "nome", column = "prd_nome"),
                                @FieldResult(name = "descricao", column = "prd_descricao"),
                                @FieldResult(name = "preco", column = "prd_preco"),
                                @FieldResult(name = "foto", column = "prd_foto"),
                                @FieldResult(name = "dataCriacao", column = "prd_data_criacao"),
                                @FieldResult(name = "dataUltimaAtualizacao",
                                        column = "prd_data_ultima_atualizacao")
                        })}),
        @SqlResultSetMapping(name = "ecm_produto.ProdutoDTO",
                classes = {
                        @ConstructorResult(targetClass = ProdutoDTO.class,
                                columns = {
                                        @ColumnResult(name = "prd_id", type = Integer.class),
                                        @ColumnResult(name = "prd_nome", type = String.class)
                                })
                })
})
@NamedQueries({
        @NamedQuery(name = "Produto.listar", query = "SELECT p FROM Produto p"),
        @NamedQuery(name = "Produto.listarPorCategoria",
                query = "SELECT p FROM Produto p WHERE EXISTS (SELECT 1 FROM Categoria c2 JOIN c2.produtos p2 WHERE p2 = p AND c2.id = :categoria)")
})
@EntityListeners({GenericoListener.class})
@Table(name = "produto",
        uniqueConstraints = {@UniqueConstraint(name = "uni_nome", columnNames = {"nome"})},
        indexes = @Index(name = "idx_nome", columnList = "nome"))
public class Produto extends EntidadeBaseInteger {

    @NotBlank
    @Column(length = 100, nullable = false)
    private String nome;

    @Lob
    private String descricao;

    @Positive
    private BigDecimal preco;

    @NotNull
    @Column(nullable = false, length = 3)
    @Convert(converter = BooleanToSimNaoConverter.class)
    private Boolean ativo = Boolean.FALSE;

    @NotNull
    @PastOrPresent
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @PastOrPresent
    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @Lob
    private byte[] foto;

    @ManyToMany
    @JoinTable(name = "produto_categoria",
            joinColumns = @JoinColumn(name = "produto_id",
                    foreignKey = @ForeignKey(name = "fk_produto_categoria_produto"), nullable = false),
            foreignKey = @ForeignKey(name = "fk_produto_categoria_categoria"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id", nullable = false))
    private List<Categoria> categorias;

    @OneToOne(mappedBy = "produto")
    private Estoque estoque;

    @ElementCollection
    @CollectionTable(name = "produto_tag",
            joinColumns = @JoinColumn(name = "produto_id"))
    @Column(name = "tag", length = 50, nullable = false)
    private List<String> tags;

    @ElementCollection
    @CollectionTable(name = "produto_atributo",
            joinColumns = @JoinColumn(name = "produto_id"))
    private List<Atributo> atributos;
}
