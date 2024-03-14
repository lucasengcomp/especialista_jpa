package com.lucasengcomp.ecommerce.criteria;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.Pedido;
import com.lucasengcomp.ecommerce.model.abstractclass.Pagamento;
import com.lucasengcomp.ecommerce.model.enums.StatusPagamento;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class JoinCriteriaTest extends EntityManagerTest {

    @Test
    public void fazerJoinComON() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);
        Join<Pedido, Pagamento> joinPagamento = root.join("pagamento");

        joinPagamento.on(criteriaBuilder.equal(joinPagamento.get("status"), StatusPagamento.PROCESSANDO));
        criteriaQuery.select(root);

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Pedido> lista = typedQuery.getResultList();

        Assert.assertTrue(lista.size() == 1);
    }

    @Test
    public void fazerJoinPedidoPagamento() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pagamento> criteriaQuery = criteriaBuilder.createQuery(Pagamento.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);
        Join<Pedido, Pagamento> joinPagamento = root.join("pagamento");

        criteriaQuery.select(joinPagamento);
        criteriaQuery.where(criteriaBuilder.equal(joinPagamento.get("status"), StatusPagamento.PROCESSANDO));


        TypedQuery<Pagamento> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Pagamento> lista = typedQuery.getResultList();

        Assert.assertTrue(lista.size() == 1);
    }
}