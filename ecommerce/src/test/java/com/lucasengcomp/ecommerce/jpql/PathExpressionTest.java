package com.lucasengcomp.ecommerce.jpql;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class PathExpressionTest extends EntityManagerTest {

    @Test
    public void buscaProdutoPorIdViaPathExpressions() {
        String jpql = "SELECT ped FROM Pedido ped JOIN ped.itens item JOIN item.produto prod WHERE prod.id = 1";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
        Assert.assertTrue(lista.size() == 2);
    }

    @Test
    public void buscaClientePorPathExpressions() {
        String jpql = "select p from Pedido p where p.cliente.nome = 'Lucas Galvao'";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }
}
