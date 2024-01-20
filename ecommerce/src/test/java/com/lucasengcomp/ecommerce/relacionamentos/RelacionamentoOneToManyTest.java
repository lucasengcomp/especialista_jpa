package com.lucasengcomp.ecommerce.relacionamentos;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.Cliente;
import com.lucasengcomp.ecommerce.model.Pedido;
import com.lucasengcomp.ecommerce.model.enums.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RelacionamentoOneToManyTest extends EntityManagerTest {

    @Test
    public void verificarRelacionamentoClientePedido() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setTotal(BigDecimal.TEN);
        pedido.setCliente(cliente);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertFalse(clienteVerificacao.getPedidos().isEmpty());
    }

    @Test
    public void verificarRelacionamentoItemsPedido() {
        /**
         Cliente cliente = entityManager.find(Cliente.class, 1);
         Produto produto = entityManager.find(Produto.class, 1);

         EnderecoEntregaPedido endereco = new EnderecoEntregaPedido();
         Pedido pedido = new Pedido();
         ItemPedido itemPedido = new ItemPedido();

         endereco.setBairro("Asa Norte");
         endereco.setCep("12323-111");
         endereco.setCidade("Brasília");
         endereco.setEstado("DF");
         endereco.setNumero("12345");
         endereco.setLogradouro("Fantasia");

         pedido.setStatusPedido(StatusPedido.AGUARDANDO);
         pedido.setDataCriacao(LocalDateTime.now());
         pedido.setTotal(BigDecimal.TEN);
         pedido.setCliente(cliente);
         pedido.setEnderecoEntrega(endereco);

         itemPedido.setPrecoProduto(BigDecimal.TEN);
         itemPedido.setPedido(pedido);
         itemPedido.setProduto(produto);
         itemPedido.setQuantidade(11);
         itemPedido.setPrecoProduto(new BigDecimal(25.50));

         entityManager.getTransaction().begin();
         entityManager.persist(pedido);
         entityManager.persist(itemPedido);
         entityManager.getTransaction().commit();
         entityManager.clear();

         ItemPedido itemPedidoPersistido = entityManager.find(ItemPedido.class, itemPedido.getId());
         Assert.assertNotNull(itemPedidoPersistido);*/
    }
}
