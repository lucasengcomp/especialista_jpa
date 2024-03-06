package com.lucasengcomp.ecommerce.jpql;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.NotaFiscal;
import com.lucasengcomp.ecommerce.model.Pedido;
import com.lucasengcomp.ecommerce.model.enums.StatusPagamento;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class PassandoParametrosTest extends EntityManagerTest {

    @Test
    public void passarParametroDate() {
        String jpql = "select nf from NotaFiscal nf where nf.dataEmissao <= ?1";

        TypedQuery<NotaFiscal> typedQuery = entityManager.createQuery(jpql, NotaFiscal.class);
        typedQuery.setParameter(1, new Date(), TemporalType.TIMESTAMP);

        List<NotaFiscal> lista = typedQuery.getResultList();
        Assert.assertTrue(lista.size() == 1);
    }

    @Test
    public void passarParametroComJoin() {
        String jpql = "SELECT p FROM Pedido p JOIN p.pagamento pag " +
                " WHERE p.id = :pedidoId AND pag.status = :pagamentoStatus ";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        typedQuery.setParameter("pedidoId", 2);
        typedQuery.setParameter("pagamentoStatus", StatusPagamento.PROCESSANDO);

        List<Pedido> lista = typedQuery.getResultList();
        Assert.assertTrue(lista.size() == 1);
    }

    @Test
    public void passarParametro() {
        String jpql = "SELECT p FROM Pedido p WHERE p.id =?1";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        typedQuery.setParameter(1, 2);

        List<Pedido> lista = typedQuery.getResultList();
        Assert.assertTrue(lista.size() == 1);
    }
}
