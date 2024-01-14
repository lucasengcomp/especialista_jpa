package com.lucasengcomp.ecommerce.model;

import com.lucasengcomp.ecommerce.model.enums.StatusPagamento;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;


@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PagamentoCartao {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    private Integer pedidoId;
    private StatusPagamento statusPagamento;
    private String numero;
}
