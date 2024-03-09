package com.lucasengcomp.ecommerce.jpql;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class FuncoesStringTest extends EntityManagerTest {

    @Test
    public void aplicarFuncaoLengthComCondicao() {
        String jpql = "SELECT c.nome, LENGTH(c.nome) FROM Categoria c WHERE LENGTH(c.nome) > 10 ";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void aplicarFuncaoTrim() {
        String jpql = "SELECT TRIM(c.nome) FROM Categoria c";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void aplicarFuncaoUpper() {
        String jpql = "SELECT UPPER(c.nome) FROM Categoria c";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void aplicarFuncaoLower() {
        String jpql = "SELECT LOWER(c.nome) FROM Categoria c";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void aplicarFuncaoSubstring() {
        String jpql = "SELECT SUBSTRING(c.nome, 1, 2) FROM Categoria c";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void aplicarFuncaoLocate() {
        String jpql = "SELECT LOCATE('a', c.nome) FROM Categoria c";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void aplicarFuncaoLength() {
        String jpql = "SELECT LENGTH(c.nome) FROM Categoria c";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void aplicarFuncaoConcat() {
        String jpql = "SELECT CONCAT('Categoria: ', c.nome) FROM Categoria c";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }
}
