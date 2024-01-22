package com.lucasengcomp.ecommerce.model;


import com.lucasengcomp.ecommerce.pk.EntidadeBaseInteger;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "estoque")
public class Estoque extends EntidadeBaseInteger {

    @Column
    private Integer quantidade;


    @OneToOne(optional = false)
    @JoinColumn(name = "produto_id")
    private Produto produto;
}
