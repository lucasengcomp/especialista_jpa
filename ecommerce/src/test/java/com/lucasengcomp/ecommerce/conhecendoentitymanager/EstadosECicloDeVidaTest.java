package com.lucasengcomp.ecommerce.conhecendoentitymanager;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.Categoria;
import org.junit.Test;

public class EstadosECicloDeVidaTest extends EntityManagerTest {

    @Test
    public void analisarEstados() {
        Categoria categoriaNovo = new Categoria();
        categoriaNovo.setNome("Eletrônicos");

        Categoria categoriaGerenciadaMerge = entityManager.merge(categoriaNovo);

        Categoria categoriaGerenciada = entityManager.find(Categoria.class, 1);

        entityManager.remove(categoriaGerenciada);
        entityManager.persist(categoriaGerenciada);
        entityManager.detach(categoriaGerenciada);
        entityManager.close();
    }
}
