package com.lucasengcomp.ecommerce.consultasnativas;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.Cliente;
import org.junit.Test;

import javax.persistence.Query;
import java.util.List;

public class ViewTest extends EntityManagerTest {

    @Test
    public void executarView() {
        Query query = entityManager.createNativeQuery(
                "SELECT cli.id, cli.nome, SUM(ped.total) " +
                        " FROM pedido ped " +
                        " JOIN view_clientes_acima_media cli on cli.id = ped.cliente_id " +
                        " GROUP BY ped.cliente_id");

        List<Object[]> lista = query.getResultList();

        lista.stream().forEach(arr -> System.out.println(
                String.format("Cliente => ID: %s, Nome: %s, Total: %s", arr)));
    }

    @Test
    public void executarViewRetornandoCliente() {
        Query query = entityManager.createNativeQuery(
                "SELECT * FROM view_clientes_acima_media", Cliente.class);

        List<Cliente> lista = query.getResultList();

        lista.stream().forEach(obj -> System.out.println(
                String.format("Cliente => ID: %s, Nome: %s", obj.getId(), obj.getNome())));
    }
}
