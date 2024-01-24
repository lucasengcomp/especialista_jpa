package com.lucasengcomp.ecommerce.jpql;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;

public class BasicoJPQLTest extends EntityManagerTest {

    @Test
    public void buscarPorIdentificador() {
        TypedQuery<Pedido> query = entityManager.createQuery(
                "SELECT p FROM Pedido p WHERE p.id = 1", Pedido.class);

        Pedido pedido = query.getSingleResult();
        Assert.assertNotNull(pedido);
    }
}
