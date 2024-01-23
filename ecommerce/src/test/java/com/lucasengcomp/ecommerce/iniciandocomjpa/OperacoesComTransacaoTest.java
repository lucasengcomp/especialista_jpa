package com.lucasengcomp.ecommerce.iniciandocomjpa;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OperacoesComTransacaoTest extends EntityManagerTest {

    @Test
    public void impedirOperacaoComDB() {
        Produto produto = entityManager.find(Produto.class, 1);
        entityManager.getTransaction().begin();
        entityManager.detach(produto);

        produto.setNome("Kindle Paperwhite 2º geração");
        produto.setDescricao("Conheça o mais novo kindle");
        produto.setPreco(new BigDecimal(799));
        produto.setDataCriacao(LocalDateTime.now());
        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoPersistido = entityManager.find(Produto.class, produto.getId());
        Assert.assertEquals("Kindle", produtoPersistido.getNome());
    }

    @Test
    public void mostrarDiferencaPersistEMerge() {
        Produto produto = new Produto();

        produto.setNome("Nokia tijolão");
        produto.setDescricao("Um telefone raiz, indestrutível!");
        produto.setPreco(new BigDecimal(200));
        produto.setDataCriacao(LocalDateTime.now());

        entityManager.getTransaction().begin();
        produto = entityManager.merge(produto);
        produto.setNome("Nokia tijolão melhorado");
        produto.setDescricao("Nikia Tijolão de luzinha");

        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoPersistido = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoPersistido);
    }

    @Test
    public void inserirObjetoComMerge() {
        Produto produto = new Produto();

        produto.setNome("Microfone Rode Videmic");
        produto.setDescricao("A melhor qualidade de som.");
        produto.setPreco(new BigDecimal(2000));
        produto.setDataCriacao(LocalDateTime.now());

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoPersistido = entityManager.find(Produto.class, 3);
        Assert.assertNotNull(produtoPersistido);
    }

    @Test
    public void atualizarObjetoGerenciado() {
        Produto produto = entityManager.find(Produto.class, 1);

        entityManager.getTransaction().begin();
        produto.setNome("Kindle Paperwhite 2º geração");
        produto.setDescricao("Conheça o mais novo kindle");
        produto.setPreco(new BigDecimal(799));
        produto.setDataCriacao(LocalDateTime.now());

        entityManager.getTransaction().commit();

        Produto produtoPersistido = entityManager.find(Produto.class, produto.getId());
        Assert.assertEquals("Kindle Paperwhite 2º geração", produtoPersistido.getNome());
    }

    @Test
    public void atualizarObjeto() {
        Produto produto = new Produto();

        produto.setNome("Kindle Paperwhite");
        produto.setDescricao("Conheça o novo kindle");
        produto.setPreco(new BigDecimal(599));
        produto.setDataCriacao(LocalDateTime.now());

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoPersistido = entityManager.find(Produto.class, 3);
        Assert.assertNotNull(produtoPersistido);
        Assert.assertEquals("Kindle Paperwhite", produto.getNome());
        entityManager.close();
    }

    @Test
    public void removerObjeto() {
        Produto produto = entityManager.find(Produto.class, 99);

        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();

        Produto produtoPersistido = entityManager.find(Produto.class, produto.getId());
        Assert.assertNull(produtoPersistido);
    }

    @Test
    public void inserirOPrimeiroObjeto() {
        Produto produto = new Produto();

        produto.setNome("Câmera Canon");
        produto.setDescricao("A melhor definição para suas fotos.");
        produto.setPreco(new BigDecimal(5000));
        produto.setDataCriacao(LocalDateTime.now());

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoPersistido = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoPersistido);
    }

    @Test
    public void abrirEFecharATransacao() {
//        Produto produto = new Produto(); // Somente para o método não mostrar erros.

        entityManager.getTransaction().begin();

//        entityManager.persist(produto);
//        entityManager.merge(produto);
//        entityManager.remove(produto);

        entityManager.getTransaction().commit();
    }
}
