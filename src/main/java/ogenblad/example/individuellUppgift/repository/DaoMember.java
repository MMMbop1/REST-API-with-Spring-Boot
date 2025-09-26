package ogenblad.example.individuellUppgift.repository;

import ogenblad.example.individuellUppgift.entity.Member;
import java.util.List;
import java.util.Optional;

public interface DaoMember {

    Member save(Member member);

    List<Member> saveAll(List<Member> members);

    Optional<Member> find(Long id);

    List<Member> findAll();

    Member update(Member member);
}
