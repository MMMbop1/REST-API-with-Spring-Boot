package org.ogenblad.spring.rest.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.ogenblad.spring.rest.security.AppUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DaoUserImpl implements DaoUser {

    @PersistenceContext
    private final EntityManager entityManager;

    public DaoUserImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<AppUser> findByUsername(String username) {
        TypedQuery<AppUser> typedQuery = entityManager.createQuery("SELECT a FROM AppUser a WHERE a.username = :username", AppUser.class);
        typedQuery.setParameter("username", username);
        try {
            return Optional.of(typedQuery.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }
}
