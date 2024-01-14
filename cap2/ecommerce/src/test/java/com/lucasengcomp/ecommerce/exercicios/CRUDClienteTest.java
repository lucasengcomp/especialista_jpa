package com.lucasengcomp.ecommerce.exercicios;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.Cliente;
import org.junit.Test;

import static org.junit.Assert.*;


public class CRUDClienteTest extends EntityManagerTest {

    @Test
    public void inserirCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(3);
        cliente.setNome("Jack Sparrow");

        iniciaTransacao();
        entityManager.persist(cliente);
        commitaTransacao();

        Cliente clientePersistido = buscaClienteNoDB(3);

        assertNotNull(clientePersistido);
        assertEquals("Jack Sparrow", clientePersistido.getNome());
        fechaTransacao();
    }

    @Test
    public void buscarCliente() {
        Cliente cliente = buscaClienteNoDB(1);

        assertNotNull(cliente);
        assertEquals("Lucas Galvao", cliente.getNome());
        fechaTransacao();
    }

    @Test
    public void atualizarCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(2);
        cliente.setNome("Capitão Jack Sparrow");

        iniciaTransacao();
        entityManager.merge(cliente);
        commitaTransacao();

        Cliente clienteAtualizado = buscaClienteNoDB(2);
        assertEquals("Capitão Jack Sparrow", clienteAtualizado.getNome());
        fechaTransacao();
    }

    @Test
    public void deletarCliente() {
        Cliente cliente = buscaClienteNoDB(5);

        iniciaTransacao();
        entityManager.remove(cliente);
        commitaTransacao();

        Cliente clienteRemovido = buscaClienteNoDB(5);
        assertNull(clienteRemovido);
        fechaTransacao();
    }

    private Cliente buscaClienteNoDB(Integer idCliente) {
        return entityManager.find(Cliente.class, idCliente);
    }

    private void fechaTransacao() {
        entityManager.close();
    }

    private void iniciaTransacao() {
        entityManager.getTransaction().begin();
    }

    private void commitaTransacao() {
        entityManager.getTransaction().commit();
        entityManager.clear();
    }
}
