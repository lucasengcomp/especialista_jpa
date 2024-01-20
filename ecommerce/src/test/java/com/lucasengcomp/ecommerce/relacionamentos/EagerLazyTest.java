package com.lucasengcomp.ecommerce.relacionamentos;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.Pedido;
import org.junit.Test;

public class EagerLazyTest extends EntityManagerTest {

    @Test
    public void verificarRelacionamentoOneToOnePedidoComPagamentoCartao() {
        Pedido pedido = entityManager.find(Pedido.class, 1);
        //Assert.assertFalse(pedido.getItemsPedido().isEmpty()); só necessário se e somente se ativado o fetch = FetchType.EAGER na coluna itemsPedido
    }
}
