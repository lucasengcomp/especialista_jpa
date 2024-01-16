package com.lucasengcomp.ecommerce.model;

import com.lucasengcomp.ecommerce.embeddables.EnderecoEntregaPedido;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itemsPedido;

    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @Column(name = "nota_fiscal_id")
    private Integer notaFiscalId;

    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pedido")
    private StatusPedido statusPedido;

    @Embedded
    private EnderecoEntregaPedido enderecoEntrega;
}
