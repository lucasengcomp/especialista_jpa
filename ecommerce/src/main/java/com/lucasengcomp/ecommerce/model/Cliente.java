package com.lucasengcomp.ecommerce.model;

import com.lucasengcomp.ecommerce.model.enums.SexoCliente;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "cliente")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private SexoCliente sexoCliente;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;
}
