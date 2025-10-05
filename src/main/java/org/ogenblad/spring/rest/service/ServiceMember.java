package org.ogenblad.spring.rest.service;

import org.ogenblad.spring.rest.dto.DietMemberDto;
import org.ogenblad.spring.rest.dto.RequestMemberDto;
import org.ogenblad.spring.rest.dto.PatchMemberDto;
import org.ogenblad.spring.rest.dto.ResponseMemberDto;
import org.ogenblad.spring.rest.entity.Member;

import java.security.Principal;
import java.util.*;

public interface ServiceMember {

    List<ResponseMemberDto> findAll();

    List<DietMemberDto> findAllDietMembers();

    ResponseMemberDto find(Long id);

    ResponseMemberDto update(RequestMemberDto member, Long id);

    ResponseMemberDto update(RequestMemberDto member, Long id, Principal principal);

    ResponseMemberDto patchUpdate(PatchMemberDto patchMember, Long id);

    ResponseMemberDto save(RequestMemberDto requestMemberDto);

    List<Member> saveAll(List<Member> members);

    void delete(Long id);
}
