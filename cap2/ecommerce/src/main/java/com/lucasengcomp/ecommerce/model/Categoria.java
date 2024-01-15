package com.lucasengcomp.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter
@Setter
@Entity
@Table(name = "categoria")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Categoria {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    private String nome;

    @Column(name = "categoria_pai_id")
    private Integer catergoriaPaiId;
}
