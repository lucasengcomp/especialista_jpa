package com.lucasengcomp.ecommerce.mapeamentobasico;

import com.lucasengcomp.ecommerce.EntityManagerTest;
import com.lucasengcomp.ecommerce.model.Categoria;
import org.junit.Assert;

public class EstrategiaChavePrimariaTest extends EntityManagerTest {

    public void estrategiaGeracaoChave() {
        Categoria categoria = new Categoria();
        categoria.setNome("Eletrônico");
        entityManager.getTransaction().begin();
        entityManager.persist(categoria);
        entityManager.getTransaction().commit();

        Categoria categoriaPersistida = entityManager.find(Categoria.class, categoria.getId());
        Assert.assertNotNull(categoriaPersistida);
    }
}
