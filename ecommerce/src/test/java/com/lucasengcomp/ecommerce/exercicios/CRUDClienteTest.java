package com.lucasengcomp.ecommerce.exercicios;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.Cliente;
import com.lucasengcomp.ecommerce.model.enums.SexoCliente;
import org.junit.Test;

import static org.junit.Assert.*;


public class CRUDClienteTest extends EntityManagerTest {

    @Test
    public void inserirCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Jack Sparrow");
        cliente.setCpf("12312312311");
        cliente.setSexoCliente(SexoCliente.MASCULINO);

        iniciaTransacao();
        entityManager.persist(cliente);
        commitaTransacao();

        Cliente clientePersistido = buscaClienteNoDB(cliente.getId());

        assertNotNull(clientePersistido);
        assertEquals("Jack Sparrow", clientePersistido.getNome());
        assertEquals(SexoCliente.MASCULINO, clientePersistido.getSexoCliente());
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
        cliente.setCpf("12312312399");
        cliente.setNome("Capitão Jack Sparrow");
        cliente.setSexoCliente(SexoCliente.MASCULINO);

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
