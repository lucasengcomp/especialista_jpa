package com.lucasengcomp.ecommerce.detalhesimportantes;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityGraph;
import java.util.List;

public class ProblemaN1Test extends EntityManagerTest {

    @Test
    public void resolverComEntityGraph() {
        EntityGraph<Pedido> entityGraph = entityManager.createEntityGraph(Pedido.class);
        entityGraph.addAttributeNodes("cliente", "notaFiscal", "pagamento");

        List<Pedido> lista = entityManager
                .createQuery("SELECT p FROM Pedido p ", Pedido.class)
                .setHint("javax.persistence.loadgraph", entityGraph)
                .getResultList();

        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void resolverComFetch() {
        List<Pedido> lista = entityManager
                .createQuery("SELECT p FROM Pedido p " +
                        " JOIN FETCH p.cliente c " +
                        " JOIN FETCH p.pagamento pag " +
                        " JOIN FETCH p.notaFiscal nf", Pedido.class)
                .getResultList();

        Assert.assertFalse(lista.isEmpty());
    }
}
