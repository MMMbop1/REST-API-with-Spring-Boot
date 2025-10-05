package org.ogenblad.spring.rest.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.ogenblad.spring.rest.entity.Address;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DaoAddressImpl implements DaoAddress{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Optional<Address> find(Long id) {
        return Optional.ofNullable(entityManager.find(Address.class, id));
    }
}
