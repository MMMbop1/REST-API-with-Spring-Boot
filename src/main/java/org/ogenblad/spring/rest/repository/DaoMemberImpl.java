package org.ogenblad.spring.rest.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.ogenblad.spring.rest.entity.Member;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DaoMemberImpl implements DaoMember {

    @PersistenceContext
    private final EntityManager entityManager;

    public DaoMemberImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Member save(Member member) {
        entityManager.persist(member);
        return member;
    }

    @Override
    public List<Member> saveAll(List<Member> members) {
        for (Member member : members) {
            entityManager.persist(member);
        }
        return members;
    }

    @Override
    public Optional<Member> find(Long id) {
        return Optional.ofNullable(entityManager.find(Member.class, id));
    }

    @Override
    public List<Member> findAll() {
        TypedQuery<Member> preparedQuery = entityManager.createQuery("SELECT m FROM Member m LEFT JOIN FETCH m.address", Member.class);
        return preparedQuery.getResultList();
    }

    @Override
    public Member update(Member member) {
        return entityManager.merge(member);
    }

    @Override
    public void delete(Member member) {
        entityManager.remove(member);
    }

    @Override
    public Optional<Member> memberByDateOfBirth(String dateOfBirth) {
        TypedQuery<Member> preparedQuery = entityManager.createQuery("SELECT m FROM Member m WHERE m.dateOfBirth = :dateOfBirth", Member.class);
        preparedQuery.setParameter("dateOfBirth", dateOfBirth);

        try {
            return Optional.of(preparedQuery.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
