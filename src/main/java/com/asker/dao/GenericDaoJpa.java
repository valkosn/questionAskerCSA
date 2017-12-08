package com.asker.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class GenericDaoJpa {
    protected EntityManager entityManager;

    @PersistenceContext(unitName = "asker")
    public final void setEntityManager(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
