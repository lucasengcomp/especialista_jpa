package com.lucasengcomp.ecommerce.jpql;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class FuncoesColecoesTest extends EntityManagerTest {

    @Test
    public void aplicarFuncaoSize() {
        String jpql = "SELECT size(p.itens) FROM Pedido p WHERE size(p.itens) > 1 ";

        TypedQuery<Integer> typedQuery = entityManager.createQuery(jpql, Integer.class);

        List<Integer> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(size -> System.out.println(size));
    }
}
