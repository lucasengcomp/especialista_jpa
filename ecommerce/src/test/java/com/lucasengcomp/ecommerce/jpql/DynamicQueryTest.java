package com.lucasengcomp.ecommerce.jpql;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class DynamicQueryTest extends EntityManagerTest {

    @Test
    public void executarConsultaDinamica() {
        Produto consultado = new Produto();
        consultado.setNome("Camera GoPro");

        List<Produto> lista = pesquisar(consultado);

        Assert.assertFalse(lista.isEmpty());

        lista.forEach(produto -> System.out.println(produto));
        Assert.assertEquals("Camera GoPro", lista.get(0).getNome());
    }

    private List<Produto> pesquisar(Produto consultado) {
        StringBuilder jpql = new StringBuilder("SELECT p FROM Produto p WHERE 1 = 1");

        if (consultado.getNome() != null) {
            jpql.append(" AND p.nome LIKE CONCAT('%', :nome, '%')");
        }

        if (consultado.getDescricao() != null) {
            jpql.append(" AND p.descricao LIKE CONCAT('%', :descricao, '%')");
        }

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql.toString(), Produto.class);

        if (consultado.getNome() != null) {
            typedQuery.setParameter("nome", consultado.getNome());
        }

        if (consultado.getDescricao() != null) {
            typedQuery.setParameter("descricao", consultado.getDescricao());
        }

        return typedQuery.getResultList();
    }
}
