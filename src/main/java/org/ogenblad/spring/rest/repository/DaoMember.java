package org.ogenblad.spring.rest.repository;

import org.ogenblad.spring.rest.entity.Member;
import java.util.List;
import java.util.Optional;

public interface DaoMember {

    Member save(Member member);

    List<Member> saveAll(List<Member> members);

    Optional<Member> find(Long id);

    List<Member> findAll();

    Member update(Member member);

    void delete(Member member);

    Optional<Member> memberByDateOfBirth(String dateOfBirth);
}