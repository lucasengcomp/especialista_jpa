package com.lucasengcomp.ecommerce.relacionamentos;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

public class RemovendoEntidadeReferenciadaTest extends EntityManagerTest {

    @Test
    public void removerEntidadeRelacionada() {
        Pedido pedido = entityManager.find(Pedido.class, 1);
        Assert.assertFalse(pedido.getItemsPedido().isEmpty());

        entityManager.getTransaction().begin();
        pedido.getItemsPedido().forEach(i -> entityManager.remove(i));
        entityManager.remove(pedido);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, 1);
        Assert.assertNull(pedidoVerificacao);
    }
}