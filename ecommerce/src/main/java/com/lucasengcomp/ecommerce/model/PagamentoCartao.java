package com.lucasengcomp.ecommerce.model;

import com.lucasengcomp.ecommerce.model.enums.StatusPagamento;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "pagamento_cartao")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PagamentoCartao {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pagamento")
    private StatusPagamento statusPagamento;

    private String numero;

    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}
