package com.lucasengcomp.ecommerce.consultasnativas;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import org.junit.Test;

import javax.persistence.Query;
import java.util.List;

public class ConsultaNativaTest extends EntityManagerTest {

    @Test
    public void executarSQL() {
        String sql = "SELECT id, nome FROM produto";
        Query query = entityManager.createNativeQuery(sql);

        List<Object[]> lista = query.getResultList();

        lista.stream().forEach(arr -> System.out.println(String.format("Produto => ID: %s, Nome: %s ", arr[0], arr[1])));
    }
}
