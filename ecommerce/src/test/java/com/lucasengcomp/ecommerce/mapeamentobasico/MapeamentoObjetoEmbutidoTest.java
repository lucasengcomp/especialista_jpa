package com.lucasengcomp.ecommerce.mapeamentobasico;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.embeddables.EnderecoEntregaPedido;
import com.lucasengcomp.ecommerce.model.Pedido;
import com.lucasengcomp.ecommerce.model.enums.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MapeamentoObjetoEmbutidoTest extends EntityManagerTest {

    @Test
    public void analisarMapeamentoObjetoEmbutido() {
        EnderecoEntregaPedido enderecoEntregaPedido = new EnderecoEntregaPedido();
        enderecoEntregaPedido.setCep("12312-000");
        enderecoEntregaPedido.setLogradouro("");
        enderecoEntregaPedido.setNumero("123");
        enderecoEntregaPedido.setCidade("Uberlândia");
        enderecoEntregaPedido.setBairro("Centro");
        enderecoEntregaPedido.setEstado("MG");

        Pedido pedido = new Pedido();
        pedido.setId(1);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatusPedido(StatusPedido.AGUARDANDO);
        pedido.setTotal(new BigDecimal(1000));
        pedido.setEnderecoEntrega(enderecoEntregaPedido);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());

        Assert.assertNotNull(pedidoVerificacao);
        Assert.assertNotNull(pedidoVerificacao.getEnderecoEntrega());
        Assert.assertEquals(StatusPedido.AGUARDANDO, pedidoVerificacao.getStatusPedido());

    }
}
