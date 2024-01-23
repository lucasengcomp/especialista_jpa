package com.lucasengcomp.ecommerce.model.abstractclass;


import com.lucasengcomp.ecommerce.model.Pedido;
import com.lucasengcomp.ecommerce.model.enums.StatusPagamento;
import com.lucasengcomp.ecommerce.pk.EntidadeBaseInteger;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "pagamento")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pagamento", discriminatorType = DiscriminatorType.STRING)
public abstract class Pagamento extends EntidadeBaseInteger {

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Column(length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPagamento status;
}

