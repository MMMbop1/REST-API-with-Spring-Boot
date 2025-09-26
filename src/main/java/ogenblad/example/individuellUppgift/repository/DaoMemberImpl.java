package ogenblad.example.individuellUppgift.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import ogenblad.example.individuellUppgift.entity.Member;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DaoMemberImpl implements DaoMember {

    @PersistenceContext
    private final EntityManager entityManager;

    private final DaoAddress daoAddress;

    public DaoMemberImpl(EntityManager entityManager, DaoAddress daoAddress) {
        this.entityManager = entityManager;
        this.daoAddress = daoAddress;
    }

    @Override
    @Transactional
    public Member save(Member member) {
        entityManager.persist(member);
        return member;
    }

    @Override
    @Transactional
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
    @Transactional
    public Member update(Member member) {
        return entityManager.merge(member);
    }
}
