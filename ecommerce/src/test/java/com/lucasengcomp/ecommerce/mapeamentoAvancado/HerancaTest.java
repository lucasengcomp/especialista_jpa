package com.lucasengcomp.ecommerce.mapeamentoAvancado;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.Cliente;
import com.lucasengcomp.ecommerce.model.enums.SexoCliente;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class HerancaTest extends EntityManagerTest {

    @Test
    public void salvarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Lucas Galvao");
        cliente.setSexoCliente(SexoCliente.MASCULINO);
        cliente.setDataNascimento(LocalDate.of(1999, 1, 1));

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Cliente clientePersistido = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(clientePersistido.getSexoCliente());
    }
}
