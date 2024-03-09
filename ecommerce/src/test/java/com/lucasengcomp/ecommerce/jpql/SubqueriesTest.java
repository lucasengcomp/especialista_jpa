package com.lucasengcomp.ecommerce.jpql;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.dto.ProdutoDTO;
import com.lucasengcomp.ecommerce.model.Cliente;
import com.lucasengcomp.ecommerce.model.Pedido;
import com.lucasengcomp.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class SubqueriesTest extends EntityManagerTest {

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
