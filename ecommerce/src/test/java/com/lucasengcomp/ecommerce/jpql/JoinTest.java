package com.lucasengcomp.ecommerce.jpql;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class JoinTest extends EntityManagerTest {

    @Test
    public void usarJoinFetchParaPagamento() {
        String jpql = "select p from Pedido p join fetch p.pagamento where p.id = 1";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertTrue(lista.isEmpty());
    }

    @Test
    public void usarJoinFetchParaItens() {
        String jpql = "select p from Pedido p join fetch p.itens where p.id = 1";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarJoinFetch() {
        String jpql = "select p from Pedido p " +
                "left join fetch p.pagamento " +
                "join fetch p.cliente " +
                "left join fetch p.notaFiscal " +
                " where p.id = 1";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void fazerLeftJoin() {
        String jpql = "select p from Pedido p left join p.pagamento pag on pag.status = 'PROCESSANDO'";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void fazerJoin() {

        /**
         * select * from pedido pe
         * inner join pagamento pa
         * ON pe.id = pa.pedido_id
         * */
        String jpql = "select p from Pedido p join p.pagamento pag where pag.status = 'PROCESSANDO'";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertTrue(lista.size() == 1);
    }
}
