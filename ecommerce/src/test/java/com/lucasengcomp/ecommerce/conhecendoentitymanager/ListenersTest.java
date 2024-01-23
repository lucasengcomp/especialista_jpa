package com.lucasengcomp.ecommerce.conhecendoentitymanager;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.Cliente;
import com.lucasengcomp.ecommerce.model.Pedido;
import com.lucasengcomp.ecommerce.model.Produto;
import com.lucasengcomp.ecommerce.model.enums.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ListenersTest extends EntityManagerTest {

    @Test
    public void logCarregamentoListenerNasEntidades() {
        Produto produto = entityManager.find(Produto.class, 1);
        Pedido pedido = entityManager.find(Pedido.class, 1);
    }

    @Test
    public void acionarCallback() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Pedido pedido = new Pedido();
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setCliente(cliente);
        pedido.setTotal(BigDecimal.TEN);
        pedido.setStatus(StatusPedido.AGUARDANDO);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.flush();

        pedido.setStatus(StatusPedido.PAGO);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Pedido pedidoPersistido = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoPersistido.getDataCriacao());
        Assert.assertNotNull(pedidoPersistido.getDataUltimaAtualizacao());
    }
}
