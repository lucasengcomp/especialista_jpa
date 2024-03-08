package com.lucasengcomp.ecommerce.jpql;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class FuncoesNumerosTest extends EntityManagerTest {

    @Test
    public void aplicarFuncaoSUM() {
        String jpql = "SELECT SUM(p.total) FROM Pedido p";

        TypedQuery<Number> typedQuery = entityManager.createQuery(jpql, Number.class);

        List<Number> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println(obj));
    }

    @Test
    public void aplicarFuncaoMAX() {
        String jpql = "SELECT MAX(p.total) FROM Pedido p";

        TypedQuery<Number> typedQuery = entityManager.createQuery(jpql, Number.class);

        List<Number> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println(obj));
    }

    @Test
    public void aplicarFuncaoMIN() {
        String jpql = "SELECT MIN(p.total) FROM Pedido p";

        TypedQuery<Number> typedQuery = entityManager.createQuery(jpql, Number.class);

        List<Number> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println(obj));
    }

    @Test
    public void aplicarFuncaoCOUNT() {
        String jpql = "SELECT COUNT(p.id) FROM Pedido p";

        TypedQuery<Number> typedQuery = entityManager.createQuery(jpql, Number.class);

        List<Number> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println(obj));
    }

    @Test
    public void aplicarFuncaoAVG() {
        String jpql = "SELECT AVG(p.total) FROM Pedido p";

        TypedQuery<Number> typedQuery = entityManager.createQuery(jpql, Number.class);

        List<Number> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println(obj));
    }

    @Test
    public void aplicarFuncaoNumeroAbsoluto() {
        String jpql = "SELECT abs(p.total), mod(3, 2), sqrt(9) FROM Pedido p";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(arr[0] + " | " + arr[1] + " | " + arr[2]));
    }
}
