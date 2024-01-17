package com.lucasengcomp.ecommerce.mapeamentoAvancado;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DetalhesColumnTest extends EntityManagerTest {

    @Test
    public void imperdirInsercaoDaColunaAtuaizacao() {
        Produto produto = new Produto();
        produto.setNome("Teclado para smartfone");
        produto.setDescricao("Um bom teclado e silencioso");
        produto.setPreco(BigDecimal.TEN);
        produto.setDataCriacao(LocalDateTime.now());
        produto.setDataUltimaAtualizacao(LocalDateTime.now());

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoPersistido = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoPersistido.getDataCriacao());
        Assert.assertNull(produtoPersistido.getDataUltimaAtualizacao());
    }

    @Test
    public void impedirInsercaoDaColunaAtualizacao() {
        entityManager.getTransaction().begin();

        Produto produto = entityManager.find(Produto.class, 1);
        produto.setDataCriacao(LocalDateTime.now());
        produto.setDataUltimaAtualizacao(LocalDateTime.now());

        entityManager.merge(produto);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoAtualizado = entityManager.find(Produto.class, 1);

        Assert.assertNotEquals(produto.getDataCriacao().truncatedTo(ChronoUnit.SECONDS),
                produtoAtualizado.getDataCriacao().truncatedTo(ChronoUnit.SECONDS));

        Assert.assertEquals(produto.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS),
                produtoAtualizado.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS));
    }
}
