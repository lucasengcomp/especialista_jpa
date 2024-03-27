package com.lucasengcomp.ecommerce.embeddables;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Atributo {

    @NotBlank
    @Column(length = 100, nullable = false)
    private String nome;

    @NotBlank
    private String valor;
}
