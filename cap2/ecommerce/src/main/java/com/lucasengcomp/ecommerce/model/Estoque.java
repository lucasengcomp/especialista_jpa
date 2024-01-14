package com.lucasengcomp.ecommerce.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Estoque {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    private Integer produtoId;
    private Integer quantidade;
}
