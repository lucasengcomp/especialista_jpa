package com.lucasengcomp.ecommerce.util;

import com.lucasengcomp.ecommerce.model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class IniciarUnidadeDePersistencia {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Ecommerce-PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Produto produto = entityManager.find(Produto.class, 1);
        System.out.println(produto.getNome());

        entityManager.close();
        entityManagerFactory.close();
    }
}
