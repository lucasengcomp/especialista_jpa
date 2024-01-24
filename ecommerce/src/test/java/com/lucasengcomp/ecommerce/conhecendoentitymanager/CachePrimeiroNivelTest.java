package com.lucasengcomp.ecommerce.conhecendoentitymanager;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.Produto;
import org.junit.Test;

public class CachePrimeiroNivelTest extends EntityManagerTest {

    @Test
    public void verificarCache() {
        Produto produto = entityManager.find(Produto.class, 1);
        System.out.println(produto.getNome());
        System.out.println("================================");

        Produto produtoEncontrado = entityManager.find(Produto.class, produto.getId());
        System.out.println(produtoEncontrado.getNome());
        System.out.println("================================");
    }
}
