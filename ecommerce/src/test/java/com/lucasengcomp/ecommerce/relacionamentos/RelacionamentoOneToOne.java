package com.lucasengcomp.ecommerce.relacionamentos;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.PagamentoCartao;
import com.lucasengcomp.ecommerce.model.Pedido;
import com.lucasengcomp.ecommerce.model.enums.StatusPagamento;
import org.junit.Assert;
import org.junit.Test;

public class RelacionamentoOneToOne extends EntityManagerTest {

    @Test
    public void verificarRelacionamentoOneToOnePedidoComPagamentoCartao() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        PagamentoCartao pagamentoCartao = new PagamentoCartao();
        pagamentoCartao.setNumero("123.123.123");
        pagamentoCartao.setStatusPagamento(StatusPagamento.PROCESSANDO);
        pagamentoCartao.setPedido(pedido);

        entityManager.getTransaction().begin();
        entityManager.persist(pagamentoCartao);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificacao.getPagamentoCartao());
    }
}
