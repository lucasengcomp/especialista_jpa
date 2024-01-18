package com.lucasengcomp.ecommerce.model;

import com.lucasengcomp.ecommerce.embeddables.EnderecoEntregaPedido;
import com.lucasengcomp.ecommerce.listener.GenericoListener;
import com.lucasengcomp.ecommerce.listener.GerarNotaFiscalListener;
import com.lucasengcomp.ecommerce.model.enums.StatusPedido;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "pedido")
@EntityListeners({GerarNotaFiscalListener.class, GenericoListener.class})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_criacao", insertable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao", updatable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pedido")
    private StatusPedido statusPedido;

    @Embedded
    private EnderecoEntregaPedido enderecoEntrega;

    @OneToOne(mappedBy = "pedido")
    private PagamentoCartao pagamentoCartao;

    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
    private List<ItemPedido> itemsPedido;

    public boolean isPago() {
        return StatusPedido.PAGO.equals(statusPedido);
    }

    public void calcularTotal() {
        if (itemsPedido != null) {
            total = itemsPedido.stream().map(ItemPedido::getPrecoProduto).reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }

    @PrePersist
    public void aoPersistirUmPedido() {
        dataCriacao = LocalDateTime.now();
        calcularTotal();
    }

    @PreUpdate
    public void aoAtualizarUmPedido() {
        dataUltimaAtualizacao = LocalDateTime.now();
        calcularTotal();
    }

    @PostPersist
    public void aoCriarUmPedido() {
        System.out.println("Após perstir um pedido");
    }
}
