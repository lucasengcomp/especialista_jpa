package com.lucasengcomp.ecommerce.model;

import com.lucasengcomp.ecommerce.embeddables.Atributo;
import com.lucasengcomp.ecommerce.listener.GenericoListener;
import com.lucasengcomp.ecommerce.pk.EntidadeBaseInteger;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
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

    @Column(length = 100, nullable = false)
    private String nome;

    @Lob
    private String descricao;

    private BigDecimal preco;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

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
