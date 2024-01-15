package com.lucasengcomp.ecommerce.mapeamentobasico;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.Cliente;
import com.lucasengcomp.ecommerce.model.enums.SexoCliente;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MapeandoEnumeracoesTest extends EntityManagerTest {

    @Test
    public void testarEnum() {
        Cliente cliente = new Cliente();
        cliente.setId(99);
        cliente.setNome("Epaminondas");
        cliente.setSexoCliente(SexoCliente.MASCULINO);

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Cliente clienteVerificado = entityManager.find(Cliente.class, cliente.getId());

        assertNotNull(clienteVerificado);
    }
}
