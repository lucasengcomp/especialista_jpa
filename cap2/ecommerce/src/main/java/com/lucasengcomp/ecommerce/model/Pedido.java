package com.lucasengcomp.ecommerce.model;

import com.lucasengcomp.ecommerce.model.enums.StatusPedido;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    private LocalDateTime dataPedido;
    private LocalDateTime dataConclusao;
    private Integer notaFiscalId;
    private BigDecimal total;
    private StatusPedido statusPedido;
    private ItemPedido itemPedido;
}
