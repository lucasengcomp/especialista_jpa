package com.lucasengcomp.ecommerce.model.abstractclass;

import com.lucasengcomp.ecommerce.model.Pedido;
import com.lucasengcomp.ecommerce.model.enums.StatusPagamento;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pagamento.class)
public abstract class Pagamento_ extends com.lucasengcomp.ecommerce.pk.EntidadeBaseInteger_ {

	public static volatile SingularAttribute<Pagamento, Pedido> pedido;
	public static volatile SingularAttribute<Pagamento, StatusPagamento> status;

	public static final String PEDIDO = "pedido";
	public static final String STATUS = "status";

}

