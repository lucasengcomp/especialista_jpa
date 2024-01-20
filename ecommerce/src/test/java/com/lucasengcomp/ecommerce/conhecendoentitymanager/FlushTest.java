package com.lucasengcomp.ecommerce.conhecendoentitymanager;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.Pedido;
import com.lucasengcomp.ecommerce.model.enums.StatusPedido;
import org.junit.Test;

public class FlushTest extends EntityManagerTest {

    @Test(expected = Exception.class)
    public void chamarFlush() {
        try {
            entityManager.getTransaction().begin();
            Pedido pedido = entityManager.find(Pedido.class, 1);
            pedido.setStatus(StatusPedido.PAGO);

//            entityManager.flush();
            if (pedido.getPagamento() == null) {
                throw new RuntimeException("Pedido ainda não foi pago");
            }

            // Pedido pedidoPago = entityManager.find(Pedido.class, 1);
            // Consulta que obriga o JPA a sincronizar o que há na memória/stack
            // Pedido pedidoPago = entityManager.createQuery("select p from pedido p where p.id = 1", Pedido.class)
            //                .getSingleResult();

            // Assert.assertEquals(pedido.getStatusPedido(), pedidoPago.getStatusPedido());

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
