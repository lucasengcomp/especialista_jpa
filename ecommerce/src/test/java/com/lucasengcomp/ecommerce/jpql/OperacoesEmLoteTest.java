package com.lucasengcomp.ecommerce.jpql;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.Produto;
import org.junit.Test;

import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class OperacoesEmLoteTest extends EntityManagerTest {
    private static final int LIMITE_INSERCOES = 4;

    @Test
    public void atualizarEmLotePrecoComId() {
        entityManager.getTransaction().begin();

        String jpql = "update Produto p set p.preco = p.preco + (p.preco * :valor) " +
                " where exists (select 1 from p.categorias c2 where c2.id = :categoria)";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("categoria", 2);
        query.setParameter("valor", new BigDecimal("0.1"));
        query.executeUpdate();

        entityManager.getTransaction().commit();
    }

   @Test
    public void atualizarEmLotePrecoComIdEntre1e10() {
        entityManager.getTransaction().begin();

        String jpql = "UPDATE Produto p SET p.preco = p.preco + 1 WHERE id BETWEEN 1 AND 10 ";

        Query query = entityManager.createQuery(jpql);
        query.executeUpdate();

        entityManager.getTransaction().commit();
    }

    @Test
    public void inserirEmLote() throws IOException {
        InputStream in = OperacoesEmLoteTest.class.getClassLoader()
                .getResourceAsStream("produtos/importar.txt");

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        entityManager.getTransaction().begin();

        int contadorInsercoes = 0;

        for(String linha: reader.lines().collect(Collectors.toList())) {
            if (linha.isBlank()) {
                continue;
            }

            String[] produtoColuna = linha.split(";");
            Produto produto = new Produto();
            produto.setNome(produtoColuna[0]);
            produto.setDescricao(produtoColuna[1]);
            produto.setPreco(new BigDecimal(produtoColuna[2]));
            produto.setDataCriacao(LocalDateTime.now());

            entityManager.persist(produto);

            if (++contadorInsercoes == LIMITE_INSERCOES) {
                entityManager.flush();
                entityManager.clear();

                contadorInsercoes = 0;

                System.out.println("----------------------------------------------------");
            }
        }

        entityManager.getTransaction().commit();
    }
}
