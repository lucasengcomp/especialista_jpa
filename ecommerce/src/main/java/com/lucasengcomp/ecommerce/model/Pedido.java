package com.lucasengcomp.ecommerce.model;

import com.lucasengcomp.ecommerce.embeddables.EnderecoEntregaPedido;
import com.lucasengcomp.ecommerce.model.enums.StatusPedido;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


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

    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @Column(name = "nota_fiscal_id")
    private Integer notaFiscalId;
    private BigDecimal total;

    @Column(name = "status_pedido")
    private StatusPedido statusPedido;

    @Embedded
    private EnderecoEntregaPedido enderecoEntrega;

//    @Column(name = "item_pedido")
//    private ItemPedido itemPedido;
}
