package com.lucasengcomp.ecommerce.model;


import com.lucasengcomp.ecommerce.pk.EntidadeBaseInteger;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal extends EntidadeBaseInteger {

    @Lob
    private byte[] xml;

    @Column(name = "data_emissao")
    private Date dataEmissao;

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}
