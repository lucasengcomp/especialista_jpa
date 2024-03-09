package com.lucasengcomp.ecommerce.jpql;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.Cliente;
import com.lucasengcomp.ecommerce.model.Pedido;
import com.lucasengcomp.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class ExpressoesCondicionaisTest extends EntityManagerTest {

    @Test
    public void usarExpressaoINIClientes() {

        Cliente cliente1 = entityManager.find(Cliente.class, 1);
        Cliente cliente2 = entityManager.find(Cliente.class, 2);

        List<Cliente> clientes = Arrays.asList(cliente1, cliente2);

        String jpql = " SELECT p FROM Pedido p WHERE p.cliente IN (:clientes)";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        typedQuery.setParameter("clientes", clientes);

        List<Pedido> result = typedQuery.getResultList();

        Assert.assertFalse(result.isEmpty());
    }

    @Test
    public void usarExpressaoINIdPedido() {
        List<Integer> parametros = Arrays.asList(1,3,4);

        String jpql = " SELECT p FROM Pedido p WHERE p.id IN (:lista)";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        typedQuery.setParameter("lista", parametros);

        List<Pedido> result = typedQuery.getResultList();

        Assert.assertFalse(result.isEmpty());
    }

    @Test
    public void usarExpressaoCase() {
        String jpql = "SELECT p.id, " +
                " CASE TYPE(p.pagamento) " +
                "       WHEN PagamentoBoleto THEN 'Pago com boleto' " +
                "       WHEN PagamentoCartao THEN 'Pago com cartão' " +
                "       ELSE 'Não pago ainda.' " +
                " END " +
                " FROM Pedido p";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarExpressaoCondicionalDiferente() {
        String jpql = "select p from Pedido p WHERE p.total <> 100";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        List<Pedido> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarExpressaoCondicionalBetweenComData() {
        String jpql = "select p from Pedido p where p.dataCriacao BETWEEN :dataInicial AND :dataFinal ";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        typedQuery.setParameter("dataInicial", LocalDateTime.now().minusDays(2));
        typedQuery.setParameter("dataFinal", LocalDateTime.now());

        List<Pedido> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarExpressaoCondicionalBetween() {
        String jpql = "select p from Produto p where p.preco BETWEEN :precoInicial AND :precoFinal ";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        typedQuery.setParameter("precoInicial", new BigDecimal(499));
        typedQuery.setParameter("precoFinal", new BigDecimal(1400));

        List<Produto> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarExpressaoCondicionalMaiorParaDatas() {
        String jpql = "select p from Pedido p where p.dataCriacao > :dataPedido ";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        typedQuery.setParameter("dataPedido", LocalDateTime.now().minusDays(2));

        List<Pedido> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarExpressaoCondicionalMaiorIgualMenorIgual() {
        String jpql = "SELECT p from Produto p WHERE p.preco >= :precoInicial " +
                " AND p.preco <= :precoFinal ";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        typedQuery.setParameter("precoInicial", new BigDecimal(400));
        typedQuery.setParameter("precoFinal", new BigDecimal(1500));

        List<Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarExpressaoCondicionalMenorIgual() {
        String jpql = "SELECT p from Produto p WHERE p.preco <= :preco";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        typedQuery.setParameter("preco", new BigDecimal(499));

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarExpressaoCondicionalMaiorIgual() {
        String jpql = "SELECT p from Produto p WHERE p.preco >= :preco";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        typedQuery.setParameter("preco", new BigDecimal(499));

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarExpressaoCondicionalMaior() {
        String jpql = "SELECT p from Produto p WHERE p.preco > :preco";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        typedQuery.setParameter("preco", new BigDecimal(499));

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarExpressaoCondicionalIsEmpty() {
        String jpql = "SELECT p from Produto p WHERE p.categorias IS EMPTY";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarExpressaoCondicionalIsNull() {
        String jpql = "SELECT p from Produto p WHERE p.foto IS NULL";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarExpressaoCondicionalLikeConcatAmbos() {
        String jpql = "SELECT c from Cliente c WHERE c.nome LIKE CONCAT('%', :nome, '%')";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        typedQuery.setParameter("nome", "Lucas");

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarExpressaoCondicionalLikeConcatInicio() {
        String jpql = "SELECT c from Cliente c WHERE c.nome LIKE CONCAT('%', :nome)";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        typedQuery.setParameter("nome", "Lucas");

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertTrue(lista.isEmpty());
    }

    @Test
    public void usarExpressaoCondicionalLikeConcatFim() {
        String jpql = "SELECT c from Cliente c WHERE c.nome LIKE CONCAT(:nome, '%')";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        typedQuery.setParameter("nome", "Lucas");

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarExpressaoCondicionalLike() {
        String jpql = "SELECT c from Cliente c WHERE c.nome LIKE :nome";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        typedQuery.setParameter("nome", "Lucas%");

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }
}
