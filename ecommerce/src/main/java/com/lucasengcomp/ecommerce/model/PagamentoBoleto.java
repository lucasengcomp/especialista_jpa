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
@Table(name = "pagamento_boleto")
public class PagamentoBoleto extends EntidadeBaseInteger {


    @Column(name = "pedido_id")
    private Integer pedidoId;

    @Column(name = "status_pagamento")
    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;

    @Column(name = "codigo_barras")
    private String codigoBarras;
}
