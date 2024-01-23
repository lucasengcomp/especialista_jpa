package com.lucasengcomp.ecommerce.model;


import com.lucasengcomp.ecommerce.pk.EntidadeBaseInteger;
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
    @Column(nullable = false)
    private byte[] xml;

    @Column(name = "data_emissao", nullable = false)
    private Date dataEmissao;

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id", foreignKey = @ForeignKey(name = "fk_nota_fiscal_pedido"))
    private Pedido pedido;
}
