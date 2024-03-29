package com.lucasengcomp.ecommerce.model;

import com.lucasengcomp.ecommerce.embeddables.EnderecoEntregaPedido;
import com.lucasengcomp.ecommerce.listener.GenericoListener;
import com.lucasengcomp.ecommerce.listener.GerarNotaFiscalListener;
import com.lucasengcomp.ecommerce.model.abstractclass.Pagamento;
import com.lucasengcomp.ecommerce.model.enums.StatusPedido;
import com.lucasengcomp.ecommerce.pk.EntidadeBaseInteger;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Entity
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "Pedido.dadosEssencias",
                attributeNodes = {
                        @NamedAttributeNode("dataCriacao"),
                        @NamedAttributeNode("status"),
                        @NamedAttributeNode("total"),
                        @NamedAttributeNode(
                                value = "cliente",
                                subgraph = "cli"
                        )
                },
                subgraphs = {
                        @NamedSubgraph(
                                name = "cli",
                                attributeNodes = {
                                        @NamedAttributeNode("nome"),
                                        @NamedAttributeNode("cpf")
                                }
                        )
                }
        )
})
@EntityListeners({GerarNotaFiscalListener.class, GenericoListener.class})
@Table(name = "pedido")
public class Pedido extends EntidadeBaseInteger {

    @NotNull
    @PastOrPresent
    @Column(name = "data_criacao", updatable = false, nullable = false)
    private LocalDateTime dataCriacao;

    @PastOrPresent
    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @PastOrPresent
    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @NotNull
    @Positive
    @Column(nullable = false)
    private BigDecimal total;

    @Column(length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    @OneToOne(mappedBy = "pedido")
    private Pagamento pagamento;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pedido_cliente"))
    private Cliente cliente;

    @NotEmpty
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

    @Embedded
    private EnderecoEntregaPedido enderecoEntrega;

    //    @Setter(AccessLevel.NONE)
//    @Getter(AccessLevel.NONE)
//    @Transient
//    private PersistentAttributeInterceptor persistentAttributeInterceptor;
//
//    public NotaFiscal getNotaFiscal() {
//        if (this.persistentAttributeInterceptor != null) {
//            return (NotaFiscal) persistentAttributeInterceptor
//                    .readObject(this, "notaFiscal", this. notaFiscal);
//        }
//
//        return this.notaFiscal;
//    }
//
//    public void setNotaFiscal(NotaFiscal notaFiscal) {
//        if (this.persistentAttributeInterceptor != null) {
//            this.notaFiscal = (NotaFiscal) persistentAttributeInterceptor
//                    .writeObject(this, "notaFiscal", this.notaFiscal, notaFiscal);
//        } else {
//            this.notaFiscal = notaFiscal;
//        }
//    }
//
//    public Pagamento getPagamento() {
//        if (this.persistentAttributeInterceptor != null) {
//            return (Pagamento) persistentAttributeInterceptor
//                    .readObject(this, "pagamento", this.pagamento);
//        }
//
//        return this.pagamento;
//    }
//
//    public void setPagamento(Pagamento pagamento) {
//        if (this.persistentAttributeInterceptor != null) {
//            this.pagamento = (Pagamento) persistentAttributeInterceptor
//                    .writeObject(this, "pagamento", this.pagamento, pagamento);
//        } else {
//            this.pagamento = pagamento;
//        }
//    }
//
//    @Override
//    public PersistentAttributeInterceptor $$_hibernate_getInterceptor() {
//        return this.persistentAttributeInterceptor;
//    }
//
//    @Override
//    public void $$_hibernate_setInterceptor(PersistentAttributeInterceptor persistentAttributeInterceptor) {
//        this.persistentAttributeInterceptor = persistentAttributeInterceptor;
//    }

    public boolean isPago() {
        return StatusPedido.PAGO.equals(status);
    }

    //@PrePersist
    //@PreUpdate
    public void calcularTotal() {
        if (itens != null) {
            total = itens.stream().map(item -> new BigDecimal(item.getQuantidade()).multiply(item.getPrecoProduto()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } else {
            total = BigDecimal.ZERO;
        }
    }

    @PrePersist
    public void aoPersistir() {
        dataCriacao = LocalDateTime.now();
        calcularTotal();
    }

    @PreUpdate
    public void aoAtualizar() {
        dataUltimaAtualizacao = LocalDateTime.now();
        calcularTotal();
    }

    @PostPersist
    public void aposPersistir() {
        System.out.println("Após persistir Pedido.");
    }

    @PostUpdate
    public void aposAtualizar() {
        System.out.println("Após atualizar Pedido.");
    }

    @PreRemove
    public void aoRemover() {
        System.out.println("Antes de remover Pedido.");
    }

    @PostRemove
    public void aposRemover() {
        System.out.println("Após remover Pedido.");
    }

    @PostLoad
    public void aoCarregar() {
        System.out.println("Após carregar o Pedido.");
    }
}
