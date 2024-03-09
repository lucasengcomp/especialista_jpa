package com.lucasengcomp.ecommerce.jpql;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.Cliente;
import com.lucasengcomp.ecommerce.model.Pedido;
import com.lucasengcomp.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class SubqueriesTest extends EntityManagerTest {

    @Test
    public void perquisarComSOMEProdutosQueJaForamVendidosPeloPrecoDiferenteDoAtual() {
        String jpql = "SELECT p from Produto p " +
                " WHERE p.preco <> SOME (SELECT precoProduto FROM ItemPedido WHERE produto = p) ";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }

    @Test
    public void perquisarComANYProdutosQueJaForamVendidosPeloPrecoDiferenteDoAtual() {
        String jpql = "SELECT p from Produto p " +
                " WHERE p.preco <> ANY (SELECT precoProduto FROM ItemPedido WHERE produto = p) ";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }

    @Test
    public void perquisarComANYProdutosQueJaForamVendidosPeloMenos1VezPeloPrecoAtual() {
        String jpql = "SELECT p from Produto p " +
                " WHERE p.preco = ANY (SELECT precoProduto FROM ItemPedido WHERE produto = p) ";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }

    @Test
    public void perquisarComAllProdutosQueNaoForamVendidosDepoisQueEncareceram() {
        String jpql = "SELECT p from Produto p " +
                " WHERE p.preco > ALL (SELECT precoProduto FROM ItemPedido WHERE produto = p) ";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }

    @Test
    public void perquisarComAllPrecoAtualProdutos() {
        String jpql = "SELECT p from Produto p " +
                " WHERE p.preco = ALL (SELECT precoProduto FROM ItemPedido WHERE produto = p) ";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }

    @Test
    public void perquisarComExistsExercicio() {
        String jpql = "SELECT p from Produto p " +
                " WHERE EXISTS " +
                " (SELECT 1 FROM ItemPedido WHERE produto = p AND precoProduto <> p.preco)";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }

    @Test
    public void perquisarComSubqueryExercicio() {
        String jpql = "SELECT c FROM Cliente c WHERE " +
                " (SELECT COUNT(cliente) FROM Pedido WHERE cliente = c) >= 2";

        TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);

        List<Cliente> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }

    @Test
    public void pesquisarComNotExists() {

        String jpql = "SELECT p FROM Produto p WHERE NOT EXISTS ( " +
                " SELECT 1 FROM ItemPedido ip2 " +
                " JOIN ip2.produto p2 WHERE p2 = p ) ";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        System.out.println(lista.size());
    }

    @Test
    public void pesquisarComExists() {

        String jpql = "SELECT p FROM Produto p WHERE EXISTS ( " +
                " SELECT 1 FROM ItemPedido ip2 " +
                " JOIN ip2.produto p2 WHERE p2 = p ) ";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        System.out.println(lista.size());
    }

    @Test
    public void pesquisarComIN() {

        String jpql = "SELECT p FROM Pedido p WHERE p.id IN (" +
                " SELECT p2.id FROM ItemPedido i2 " +
                " JOIN i2.pedido p2 JOIN i2.produto pro2 WHERE pro2.preco > 100 ) ";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        List<Pedido> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        System.out.println(lista.size());
    }

    @Test
    public void pesquisarProdutosMaisCaros() {

        String jpql = "SELECT p FROM Produto p WHERE " +
                " p.preco = (SELECT MAX(preco) FROM Produto)";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        System.out.println(lista.size());
        lista.forEach(p -> System.out.println("ID: " + p.getId() + ", Preço: " + p.getPreco()));
    }

    @Test
    public void pesquisarTodosPedidosAcimaMediaVendas() {

        String jpql = "SELECT p FROM Pedido p WHERE " +
                " p.total > (SELECT avg(total) FROM Pedido)";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        List<Pedido> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        System.out.println(lista.size());
    }

    @Test
    public void pesquisarBonsClientesQueGastamMenosQueCemReais() {

        String jpql = "SELECT c FROM Cliente c WHERE " +
                " 100 < (SELECT SUM(p.total) FROM c.pedidos p)";

        TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);

        List<Cliente> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        System.out.println(lista.size());
    }

    @Test
    public void pesquisarBonsClientes() {

        String jpql = "SELECT c FROM Cliente c WHERE " +
                " 50 < (SELECT SUM(p.total) FROM Pedido p " +
                " WHERE p.cliente = c)";

        TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);

        List<Cliente> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        System.out.println(lista.size());
    }
}
