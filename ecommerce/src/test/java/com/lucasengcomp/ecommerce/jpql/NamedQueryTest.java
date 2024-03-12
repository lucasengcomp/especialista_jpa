package com.lucasengcomp.ecommerce.jpql;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class NamedQueryTest extends EntityManagerTest {

    @Test
    public void executarConsultaCategoria() {

        TypedQuery<Produto> typedQuery = entityManager.createNamedQuery("Produto.listarPorCategoria", Produto.class);
        typedQuery.setParameter("categoria", 2);
        List<Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void executarConsulta() {

        TypedQuery<Produto> typedQuery = entityManager.createNamedQuery("Produto.listar", Produto.class);
        List<Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }
}
