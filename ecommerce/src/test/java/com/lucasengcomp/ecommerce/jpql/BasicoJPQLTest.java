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

public class BasicoJPQLTest extends EntityManagerTest {

    @Test
    public void usarDistinct() {

        String jpql = "SELECT DISTINCT p FROM Pedido p " +
                " JOIN p.itens i " +
                " JOIN i.produto pro " +
                " WHERE pro.id IN (1,2,3,4) ";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        List<Pedido> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        System.out.println(lista.size());
    }

    @Test
    public void ordenarResultadosCrescente() {

        String jpql = "SELECT c FROM Cliente c ORDER BY c.nome ASC";

        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);

        List<Cliente> lista = query.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void ordenarResultadosDecrescente() {

        String jpql = "SELECT c FROM Cliente c ORDER BY c.nome DESC";

        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);

        List<Cliente> lista = query.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void projetarNoDTO() {
        String jpql = "SELECT new com.lucasengcomp.ecommerce.dto.ProdutoDTO(id, nome) FROM Produto";

        TypedQuery<ProdutoDTO> typedQuery = entityManager.createQuery(jpql, ProdutoDTO.class);
        List<ProdutoDTO> list = typedQuery.getResultList();

        Assert.assertFalse(list.isEmpty());
        list.forEach(produtoDTO -> System.out.println(produtoDTO.getId() + ", " + produtoDTO.getNome()));
    }

    @Test
    public void projetarResultado() {
        String jpql = "SELECT id, nome FROM Produto";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> list = typedQuery.getResultList();

        Assert.assertTrue(list.get(0).length == 2);
        list.forEach(arr -> System.out.println(arr[0] + ", " + arr[1]));
    }

    @Test
    public void selecionarUmAtributoParaRetorno() {
        String jpql = "SELECT p FROM Produto p";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<Produto> produtos = typedQuery.getResultList();

        String jpqlCliente = "SELECT p.cliente FROM Pedido p";

        TypedQuery<Cliente> typedQueryCliente = entityManager.createQuery(jpqlCliente, Cliente.class);
        List<Cliente> clientes = typedQueryCliente.getResultList();

        System.out.println(Cliente.class.equals(clientes.get(0).getClass()));

        Assert.assertTrue(Cliente.class.equals(clientes.get(0).getClass()));
    }

    @Test
    public void buscarPorIdentificador() {
        TypedQuery<Pedido> query = entityManager.createQuery(
                "SELECT p FROM Pedido p WHERE p.id = 1", Pedido.class);

        Pedido pedido = query.getSingleResult();
        Assert.assertNotNull(pedido);
    }

    @Test
    public void mostrarDiferencaQueries() {
        String jpql = "SELECT p FROM Pedido p where p.id = 1";
        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        Pedido pedido1 = typedQuery.getSingleResult();
        Assert.assertNotNull(pedido1);

        Query query = entityManager.createQuery(jpql);
        Pedido pedido2 = (Pedido) query.getSingleResult();
        Assert.assertNotNull(pedido2);

        List<Pedido> pedidos = query.getResultList();
        Assert.assertFalse(pedidos.isEmpty());
    }
}
