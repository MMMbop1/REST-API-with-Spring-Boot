package ogenblad.example.individuellUppgift.service;

import ogenblad.example.individuellUppgift.dto.DietMemberDto;
import ogenblad.example.individuellUppgift.dto.MemberDto;
import ogenblad.example.individuellUppgift.dto.PatchMemberDto;
import ogenblad.example.individuellUppgift.entity.Member;

import java.util.*;

public interface ServiceMember {

    List<Member> findAll();

    List<DietMemberDto> findAllDietMembers();

    Member find(Long id);

    Member update(MemberDto member, Long id);

    Member patchUpdate(PatchMemberDto patchMember, Long id);

    Member save(Member postMember);

    void delete(Long id);
}
