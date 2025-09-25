package ogenblad.example.individuellUppgift.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import ogenblad.example.individuellUppgift.entity.Member;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDaoImpl implements MemberDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public MemberDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Member save(Member member) {
        entityManager.persist(member);
        return member;
    }

    @Transactional
    public List<Member> saveAll(List<Member> members) {
        for (Member member : members) {
            entityManager.persist(member);
        }
        return members;
    }

    public List<Member> findAll() {
        TypedQuery<Member> preparedQuery = entityManager.createQuery("SELECT m FROM Member m LEFT JOIN FETCH m.address", Member.class);
        return preparedQuery.getResultList();
    }
}
