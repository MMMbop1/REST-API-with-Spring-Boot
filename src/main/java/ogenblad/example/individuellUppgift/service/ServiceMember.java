package ogenblad.example.individuellUppgift.service;

import ogenblad.example.individuellUppgift.dto.MemberDto;
import ogenblad.example.individuellUppgift.entity.Member;

import java.util.*;

public interface ServiceMember {

    List<Member> findAll();

    Member find(Long id);

    Member update(Member member, Long id);

    Member patchUpdate(MemberDto patchMember, Long id);

    Member save(Member postMember);

    void delete(Long id);
}
