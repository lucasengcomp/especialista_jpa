package com.lucasengcomp.ecommerce.jpql;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class GroupByTest extends EntityManagerTest {

    @Test
    public void agruparEFiltrarTotalVendas() {
        String jpql = "SELECT c.nome, SUM(ip.precoProduto) FROM ItemPedido ip " +
                " JOIN ip.produto pro " +
                " JOIN pro.categorias c " +
                " JOIN ip.pedido p " +
                " WHERE YEAR(p.dataCriacao) = YEAR(CURRENT_DATE) " +
                " AND MONTH(p.dataCriacao) = MONTH(CURRENT_DATE) " +
                " GROUP BY c.id";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse("A lista de resultados não deve estar vazia", lista.isEmpty());

        lista.forEach(obj -> System.out.println(obj[0] + " - " + obj[1]));
    }

    @Test
    public void agruparEFiltrarTotalVendasUltimos3Meses() {
        String jpql = "SELECT c.nome, SUM(ip.precoProduto) FROM ItemPedido ip " +
                " JOIN ip.pedido p " +
                " JOIN p.cliente c " +
                " WHERE YEAR(p.dataCriacao) = YEAR(CURRENT_DATE) " +
                " AND MONTH(p.dataCriacao) >= (MONTH(CURRENT_DATE) - 3) " +
                " GROUP BY c.id";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse("A lista de resultados não deve estar vazia", lista.isEmpty());

        lista.forEach(obj -> System.out.println(obj[0] + " - " + obj[1]));
    }

    @Test
    public void agruparResultadoPorDiaECategoria() {
        String jpql = "SELECT " +
                " CONCAT(YEAR(p.dataCriacao), '/', MONTH(p.dataCriacao), '/', DAY(p.dataCriacao)), " +
                " CONCAT(c.nome, ': ', SUM(ip.precoProduto)) " +
                " FROM ItemPedido ip " +
                " JOIN ip.pedido p " +
                " JOIN ip.produto pro " +
                " JOIN pro.categorias c " +
                " GROUP BY CONCAT(YEAR(p.dataCriacao), '/', MONTH(p.dataCriacao), '/', DAY(p.dataCriacao)), c.nome ";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse("A lista de resultados não deve estar vazia", lista.isEmpty());

        lista.forEach(obj -> System.out.println(obj[0] + " - " + obj[1]));
    }

    @Test
    public void agruparResultadoPorCliente() {
        String jpql = "SELECT c.nome, SUM(ip.precoProduto) FROM ItemPedido ip " +
                " JOIN ip.pedido p JOIN p.cliente c " +
                " GROUP BY c.id";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse("A lista de resultados não deve estar vazia", lista.isEmpty());

        lista.forEach(obj -> System.out.println(obj[0] + " - " + obj[1]));
    }

    @Test
    public void agruparResultadoTotalVendasPorCategoria() {
        String jpql = "SELECT c.nome, SUM(ip.precoProduto) FROM ItemPedido ip " +
                " JOIN ip.produto pro join pro.categorias c " +
                " GROUP BY c.id";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse("A lista de resultados não deve estar vazia", lista.isEmpty());

        lista.forEach(obj -> System.out.println(obj[0] + " - " + obj[1]));
    }

    @Test
    public void agruparResultadoTotalVendasPorMes() {
        String jpql = "SELECT DATE_FORMAT(p.dataCriacao, '%Y %M'), SUM(p.total) " +
                "FROM Pedido p " +
                "GROUP BY DATE_FORMAT(p.dataCriacao, '%Y %M')";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse("A lista de resultados não deve estar vazia", lista.isEmpty());

        lista.forEach(obj -> System.out.println(obj[0] + " - " + obj[1]));
    }

    @Test
    public void agruparResultadoProdutoPorCategoria() {
        String jpql = " SELECT c.nome, COUNT(p.id) FROM Categoria c " +
                " JOIN c.produtos p" +
                " GROUP BY c.id ";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println(obj[0] + " - " + obj[1]));
    }
}
