package com.lucasengcomp.ecommerce.relacionamentos;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.Categoria;
import com.lucasengcomp.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class RelacionamentoManyToMany extends EntityManagerTest {

    @Test
    public void verificarRelacionamentoManyToMany() {
        Produto produto = entityManager.find(Produto.class, 1);
        Categoria categoria = entityManager.find(Categoria.class, 1);

        entityManager.getTransaction().begin();
        produto.setCategorias(Arrays.asList(categoria));
        entityManager.getTransaction().commit();
        entityManager.clear();

        Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());
        Assert.assertFalse(categoriaVerificacao.getProdutos().isEmpty());
    }
}
