package com.lucasengcomp.ecommerce.consultasnativas;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.dto.ProdutoDTO;
import com.lucasengcomp.ecommerce.model.Produto;
import org.junit.Test;

import javax.persistence.Query;
import java.util.List;

public class ConsultaNativaTest extends EntityManagerTest {

    @Test
    public void usarUmaNamedNativeQuery02() {
        Query query = entityManager.createNamedQuery("ecm_produto.listar");

        List<Produto> lista = query.getResultList();

        lista.stream().forEach(obj -> System.out.println(
                String.format("Produto => ID: %s, Nome: %s", obj.getId(), obj.getNome())));
    }

    @Test
    public void usarUmaNamedNativeQuery01() {
        Query query = entityManager.createNamedQuery("produto_loja.listar");

        List<Produto> lista = query.getResultList();

        lista.stream().forEach(obj -> System.out.println(
                String.format("ProdutoDTO => ID: %s, Nome: %s", obj.getId(), obj.getNome())));
    }

    @Test
    public void usarColumnResultRetornarDTO() {
        String sql = "SELECT * FROM ecm_produto";

        Query query = entityManager.createNativeQuery(sql, "ecm_produto.ProdutoDTO");

        List<ProdutoDTO> lista = query.getResultList();

        lista.stream().forEach(obj -> System.out.println(
                String.format("ProdutoDTO => ID: %s, Nome: %s", obj.getId(), obj.getNome())));
    }

    @Test
    public void usarFieldResult() {
        String sql = "SELECT * FROM ecm_produto";

        Query query = entityManager.createNativeQuery(sql, "ecm_produto.Produto");

        List<Produto> lista = query.getResultList();

        lista.stream().forEach(obj -> System.out.println(
                String.format("Produto => ID: %s, Nome: %s", obj.getId(), obj.getNome())));
    }

    @Test
    public void usarSQLResultSetMapping01() {
        String sql = " SELECT id, nome, descricao, data_criacao, data_ultima_atualizacao, preco, foto " +
                " FROM produto_loja ";

        Query query = entityManager.createNativeQuery(sql, "produto_loja.Produto");

        List<Produto> lista = query.getResultList();

        lista.stream().forEach(obj ->
                System.out.println(String.format("Produto => ID: %s, Nome: %s ", obj.getId(), obj.getNome())));
    }

    @Test
    public void passandoParametrosNaConsulta() {
        String sql = " SELECT prd_id id, prd_nome nome, prd_descricao descricao, " +
                " prd_data_criacao data_criacao, prd_data_ultima_atualizacao data_ultima_atualizacao, " +
                " prd_preco preco, prd_foto foto " +
                " FROM ecm_produto" +
                " WHERE prd_id = :id ";

        Query query = entityManager.createNativeQuery(sql, Produto.class);

        query.setParameter("id", 201);

        List<Produto> lista = query.getResultList();

        lista.stream().forEach(obj ->
                System.out.println(String.format("Produto => ID: %s, Nome: %s ", obj.getId(), obj.getNome())));
    }

    @Test
    public void retornandoEntidadeERPProduto() {
        String sql = "SELECT id, nome, descricao, " +
                " NULL data_criacao, null data_ultima_atualizacao, " +
                " preco, NULL foto " +
                " FROM erp_produto";

        Query query = entityManager.createNativeQuery(sql, Produto.class);

        List<Produto> lista = query.getResultList();

        lista.stream().forEach(obj ->
                System.out.println(String.format("Produto => ID: %s, Nome: %s ", obj.getId(), obj.getNome())));
    }

    @Test
    public void retornandoEntidadeProdutoECM() {
        String sql = " SELECT prd_id id, prd_nome nome, prd_descricao descricao, " +
                " prd_data_criacao data_criacao, prd_data_ultima_atualizacao data_ultima_atualizacao, " +
                " prd_preco preco, prd_foto foto " +
                " FROM ecm_produto ";

        Query query = entityManager.createNativeQuery(sql, Produto.class);

        List<Produto> lista = query.getResultList();

        lista.stream().forEach(obj ->
                System.out.println(String.format("Produto => ID: %s, Nome: %s ", obj.getId(), obj.getNome())));
    }

    @Test
    public void retornandoEntidadeProduto() {
        String sql = "SELECT id, nome, descricao, data_criacao, data_ultima_atualizacao, preco, foto FROM produto";
        Query query = entityManager.createNativeQuery(sql, Produto.class);

        List<Produto> lista = query.getResultList();

        lista.stream().forEach(obj ->
                System.out.println(String.format("Produto => ID: %s, Nome: %s ", obj.getId(), obj.getNome())));
    }

    @Test
    public void executarSQL() {
        String sql = "SELECT id, nome FROM produto";
        Query query = entityManager.createNativeQuery(sql);

        List<Object[]> lista = query.getResultList();

        lista.stream().forEach(arr -> System.out.println(String.format("Produto => ID: %s, Nome: %s ", arr[0], arr[1])));
    }
}
