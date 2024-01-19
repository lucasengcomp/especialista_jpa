package com.lucasengcomp.ecommerce.model;

import com.lucasengcomp.ecommerce.model.enums.StatusPagamento;
import com.lucasengcomp.ecommerce.pk.EntidadeBaseInteger;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "pagamento_cartao")
public class PagamentoCartao extends EntidadeBaseInteger {

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pagamento")
    private StatusPagamento statusPagamento;

    private String numero;

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}
