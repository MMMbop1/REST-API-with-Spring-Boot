package ogenblad.example.individuellUppgift.service;

import ogenblad.example.individuellUppgift.dto.DietMemberDto;
import ogenblad.example.individuellUppgift.dto.RequestMemberDto;
import ogenblad.example.individuellUppgift.dto.PatchMemberDto;
import ogenblad.example.individuellUppgift.dto.ResponseMemberDto;
import ogenblad.example.individuellUppgift.entity.Member;

import java.security.Principal;
import java.util.*;

public interface ServiceMember {

    List<ResponseMemberDto> findAll();

    List<DietMemberDto> findAllDietMembers();

    ResponseMemberDto find(Long id);

    Member update(RequestMemberDto member, Long id);

    Member update(RequestMemberDto member, Long id, Principal principal);

    Member patchUpdate(PatchMemberDto patchMember, Long id);

    Member save(Member postMember);

    List<Member> saveAll(List<Member> members);

    void delete(Long id);
}
