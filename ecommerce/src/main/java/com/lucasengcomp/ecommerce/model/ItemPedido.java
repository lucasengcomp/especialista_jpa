package com.lucasengcomp.ecommerce.model;


import com.lucasengcomp.ecommerce.chavecomposta.ItemPedidoId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "item_pedido")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemPedido {

    @EmbeddedId
    private ItemPedidoId id;

    @Column(name = "preco_produto")
    private BigDecimal precoProduto;

    private Integer quantidade;

    @MapsId("pedidoId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @MapsId("produtoId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id")
    private Produto produto;
}
