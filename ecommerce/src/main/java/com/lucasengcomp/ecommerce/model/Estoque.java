package com.lucasengcomp.ecommerce.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "estoque")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Estoque {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quantidade;


    @OneToOne(optional = false)
    @JoinColumn(name = "produto_id")
    private Produto produto;
}
