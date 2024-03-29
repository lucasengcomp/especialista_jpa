package com.lucasengcomp.ecommerce.detalhesimportantes;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class OneOneLazyTest extends EntityManagerTest {

    @Test
    public void mostrarProblema() {
        System.out.println("BUSCANDO UM PEDIDO:");
        Pedido pedido = entityManager
                .createQuery("SELECT p FFROM Pedido p " +
                        "LEFT JOIN FETCH p.pagamento " +
                        "LEFT JOIN FETCH p.cliente " +
                        "LEFT JOIN FETCH p.notaFiscal " +
                        "WHERE p.id = 1", Pedido.class)
                .getSingleResult();
        Assert.assertNotNull(pedido);

        System.out.println("----------------------------------------------------");

        System.out.println("BUSCANDO UMA LISTA DE PEDIDOS:");
        List<Pedido> lista = entityManager
                .createQuery("SELECT p FFROM Pedido p " +
                        "LEFT JOIN FETCH p.pagamento " +
                        "LEFT JOIN FETCH p.cliente " +
                        "LEFT JOIN FETCH p.notaFiscal", Pedido.class)
                .getResultList();
        Assert.assertFalse(lista.isEmpty());
    }
}
