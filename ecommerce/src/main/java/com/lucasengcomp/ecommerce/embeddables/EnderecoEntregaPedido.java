package com.lucasengcomp.ecommerce.embeddables;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Embeddable
public class EnderecoEntregaPedido {

    @NotNull
    @Pattern(regexp = "[0-9]{5}-[0-9]{3}")
    @Column(length = 9)
    private String cep;

    @NotBlank
    @Column(length = 100)
    private String logradouro;

    @NotBlank
    @Column(length = 10)
    private String numero;

    @NotBlank
    @Column(length = 50)
    private String bairro;

    @NotBlank
    @Column(length = 50)
    private String cidade;

    @NotBlank
    @Column(length = 2)
    private String estado;
}
