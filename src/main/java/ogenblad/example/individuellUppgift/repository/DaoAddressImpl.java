package ogenblad.example.individuellUppgift.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import ogenblad.example.individuellUppgift.entity.Address;
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
