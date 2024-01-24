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

public class CascadeTypeMergeTest extends EntityManagerTest {

    @Test
    public void atualizarProdutoComCategoria() {
        Produto produto = new Produto();
        produto.setId(1);
        produto.setDataUltimaAtualizacao(LocalDateTime.now());
        produto.setPreco(new BigDecimal(500));
        produto.setNome("Monitor X");
        produto.setDescricao("Um excelente monitor de 34 polegadas 4K.");

        Categoria categoria = new Categoria();
        categoria.setId(2);
        categoria.setNome("Escritório");

        produto.setCategorias(Arrays.asList(categoria)); // CascadeType.MERGE na anotação de Categoria na classe Produto

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Categoria categoriaPersistida = entityManager.find(Categoria.class, categoria.getId());
        Assert.assertEquals("Escritório", categoriaPersistida.getNome());
    }

    //@Test
    public void salvarProdutoComCategoria() {
        Produto produto = new Produto();
        produto.setDataCriacao(LocalDateTime.now());
        produto.setDataUltimaAtualizacao(LocalDateTime.now());
        produto.setPreco(new BigDecimal(1000));
        produto.setNome("Condesador de audio");
        produto.setDescricao("Um ótimo condensador de áudio para conversação");

        Categoria categoria = new Categoria();
        categoria.setNome("Eletronico");

        produto.setCategorias(Arrays.asList(categoria));

        entityManager.getTransaction().begin();
        entityManager.persist(produto); // Adicionar (cascade = CascadeType.PERSIST) na anotação de relacionamento
        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoPersistido = entityManager.find(Produto.class, produto.getId()) ;

        Assert.assertNotNull(produtoPersistido);
    }

    // @Test
    public void atualizarPedidoComItens() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setId(1);
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.AGUARDANDO);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId());
        itemPedido.getId().setPedidoId(pedido.getId());
        itemPedido.getId().setProdutoId(produto.getId());
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(3);
        itemPedido.setPrecoProduto(produto.getPreco());

        pedido.setItens(Arrays.asList(itemPedido)); // CascadeType.MERGE

        entityManager.getTransaction().begin();
        entityManager.merge(pedido);
        entityManager.getTransaction().commit();
        entityManager.clear();

        ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, itemPedido.getId());
        Assert.assertTrue(itemPedidoVerificacao.getQuantidade().equals(3));
    }

    // @Test
    public void atualizarItemPedidoComPedido() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setId(1);
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.PAGO);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId());
        itemPedido.getId().setPedidoId(pedido.getId());
        itemPedido.getId().setProdutoId(produto.getId());
        itemPedido.setPedido(pedido); // CascadeType.MERGE
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(5);
        itemPedido.setPrecoProduto(produto.getPreco());

        pedido.setItens(Arrays.asList(itemPedido));

        entityManager.getTransaction().begin();
        entityManager.merge(itemPedido);
        entityManager.getTransaction().commit();
        entityManager.clear();

        ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, itemPedido.getId());
        Assert.assertTrue(StatusPedido.PAGO.equals(itemPedidoVerificacao.getPedido().getStatus()));
    }
}
