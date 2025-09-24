package ogenblad.example.individuellUppgift.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ogenblad.example.individuellUppgift.entity.Member;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO {

    @PersistenceContext
    private final EntityManager entityManager;

    public MemberDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Member save(Member member) {
        entityManager.persist(member);
        return member;
    }
}
