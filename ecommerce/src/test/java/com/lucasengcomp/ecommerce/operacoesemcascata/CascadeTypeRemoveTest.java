package com.lucasengcomp.ecommerce.operacoesemcascata;


import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.chavecomposta.ItemPedidoId;
import com.lucasengcomp.ecommerce.model.*;
import com.lucasengcomp.ecommerce.model.enums.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

public class CascadeTypeRemoveTest extends EntityManagerTest {

//     @Test
    public void removerItensOrfaos() {
        // adicionar a propriedade orphanRemoval em Pedido > itens, para o teste funcionar
        Pedido pedido = entityManager.find(Pedido.class, 1);

        Assert.assertFalse(pedido.getItens().isEmpty());

        entityManager.getTransaction().begin();
        pedido.getItens().clear();
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertTrue(pedidoVerificacao.getItens().isEmpty());
    }

    @Test
    public void removerRelacaoProdutoCategoria() {
        Produto produto = entityManager.find(Produto.class, 1);

        Assert.assertFalse(produto.getCategorias().isEmpty());

        entityManager.getTransaction().begin();
        produto.getCategorias().clear();
        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertTrue(produtoVerificacao.getCategorias().isEmpty());
    }

//    @Test
    public void removerPedidoComItens() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        entityManager.getTransaction().begin();
//        pedido.getItens().forEach(itemPedido -> entityManager.remove(itemPedido)); //caso não fosse passar anotação CascadeType.REMOVE
        entityManager.remove(pedido); //CascadeType.REMOVE deve ser adicionado na classe Pedido no itens
        entityManager.getTransaction().commit();
        entityManager.clear();

        Pedido pedidoRemovido = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNull(pedidoRemovido);
    }

//    @Test
    public void removerItemPedidoEPedido() {
        ItemPedido itemPedido = entityManager.find(ItemPedido.class, new ItemPedidoId(1, 1));

        entityManager.getTransaction().begin();
        entityManager.remove(itemPedido); //D eve ser adicionado  no ItemPedido: @ManyToOne(optional = false, cascade = CascadeType.REMOVE)
        entityManager.getTransaction().commit();
        entityManager.clear();

        Pedido pedidoRemovido = entityManager.find(Pedido.class, itemPedido.getPedido().getId());
        Assert.assertNull(pedidoRemovido);
    }
}
