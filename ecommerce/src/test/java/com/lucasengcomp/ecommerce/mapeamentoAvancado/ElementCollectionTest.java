package com.lucasengcomp.ecommerce.mapeamentoAvancado;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.embeddables.Atributo;
import com.lucasengcomp.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;


public class ElementCollectionTest extends EntityManagerTest {

    @Test
    public void aplicarTags() {
        entityManager.getTransaction().begin();
        Produto produto = entityManager.find(Produto.class, 1);

        produto.setTags(Arrays.asList("Ebook", "Livro digital"));

        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());

        Assert.assertFalse( produtoVerificacao.getTags().isEmpty());
    }

    @Test
    public void aplicarAtributos() {
        entityManager.getTransaction().begin();
        Produto produto = entityManager.find(Produto.class, 1);

        produto.setAtributos(Arrays.asList(new Atributo("Tela", "32x600")));

        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());

        Assert.assertFalse( produtoVerificacao.getAtributos().isEmpty());
    }
}
