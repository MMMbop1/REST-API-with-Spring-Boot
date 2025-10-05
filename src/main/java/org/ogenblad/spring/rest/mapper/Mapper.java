package org.ogenblad.spring.rest.mapper;

import org.ogenblad.spring.rest.dto.RequestMemberDto;
import org.ogenblad.spring.rest.dto.ResponseMemberDto;
import org.ogenblad.spring.rest.entity.Address;
import org.ogenblad.spring.rest.entity.Member;
import org.ogenblad.spring.rest.dto.DietMemberDto;
import org.ogenblad.spring.rest.security.AppUser;
import org.springframework.stereotype.Component;


@Component
public class Mapper {

    public static DietMemberDto toDietMemberDto(Member member) {
        return new DietMemberDto(member.getFirstName(), member.getLastName(), member.getAddress(), member.getEmail(), member.getPhone());
    }

    public static Member memberDtoToMember(RequestMemberDto requestMemberDto, Address address) {
        return new Member(requestMemberDto.firstName(), requestMemberDto.lastName(), address, requestMemberDto.email(), requestMemberDto.phone(), requestMemberDto.dateOfBirth());
    }

    public static Member memberDtoToMember(RequestMemberDto requestMemberDto, Address address, AppUser appUser) {
        return new Member(requestMemberDto.firstName(), requestMemberDto.lastName(), address, requestMemberDto.email(), requestMemberDto.phone(), requestMemberDto.dateOfBirth(), appUser);
    }

    public static ResponseMemberDto toResponseMemberDto(Member member) {
        return new ResponseMemberDto(member.getId(), member.getFirstName(), member.getLastName(), member.getAddress(), member.getEmail(), member.getPhone(), member.getDateOfBirth());
    }
}
