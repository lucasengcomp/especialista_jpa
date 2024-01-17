package com.lucasengcomp.ecommerce.relacionamentos;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

public class EagerLazyTest extends EntityManagerTest {

    @Test
    public void verificarRelacionamentoOneToOnePedidoComPagamentoCartao() {
        Pedido pedido = entityManager.find(Pedido.class, 1);
        Assert.assertFalse(pedido.getItemsPedido().isEmpty());
    }
}
