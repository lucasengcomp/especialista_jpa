package com.lucasengcomp.ecommerce.model;

import com.lucasengcomp.ecommerce.model.abstractclass.Pagamento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Setter
@Entity
@Table(name = "pagamento_boleto")
@DiscriminatorValue("boleto")
public class PagamentoBoleto extends Pagamento {

    @Column(name = "codigo_barras", length = 100)
    private String codigoBarras;
}
