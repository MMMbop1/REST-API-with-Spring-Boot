package ogenblad.example.individuellUppgift.mapper;

import ogenblad.example.individuellUppgift.dto.RequestMemberDto;
import ogenblad.example.individuellUppgift.dto.ResponseMemberDto;
import ogenblad.example.individuellUppgift.entity.Address;
import ogenblad.example.individuellUppgift.entity.Member;
import ogenblad.example.individuellUppgift.dto.DietMemberDto;
import ogenblad.example.individuellUppgift.security.AppUser;
import org.springframework.stereotype.Component;


@Component
public class Mapper {

    public static DietMemberDto toDietMembertDto(Member member) {
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
