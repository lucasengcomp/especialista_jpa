package com.lucasengcomp.ecommerce.mapeamentobasico;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.Cliente;
import com.lucasengcomp.ecommerce.model.enums.SexoCliente;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MapeandoEnumeracoesTest extends EntityManagerTest {

    @Test
    public void testarEnum() {
        Cliente cliente = new Cliente();
//        cliente.setId(4); Comentado porque estamos utilizando IDENTITY
        cliente.setNome("Epaminondas");
        cliente.setSexoCliente(SexoCliente.MASCULINO);
        cliente.setCpf("77755544433");

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(clienteVerificacao);
    }
}
