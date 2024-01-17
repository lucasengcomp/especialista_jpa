package com.lucasengcomp.ecommerce.conhecendoentitymanager;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.Cliente;
import com.lucasengcomp.ecommerce.model.Pedido;
import com.lucasengcomp.ecommerce.model.Produto;
import com.lucasengcomp.ecommerce.model.enums.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class CallbacksTest extends EntityManagerTest {

    @Test
    public void acionarCallback() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setStatusPedido(StatusPedido.AGUARDANDO);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.flush();

        pedido.setStatusPedido(StatusPedido.PAGO);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Pedido pedidoPersistido = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoPersistido.getDataCriacao());
        Assert.assertNotNull(pedidoPersistido.getDataUltimaAtualizacao());

    }
}
