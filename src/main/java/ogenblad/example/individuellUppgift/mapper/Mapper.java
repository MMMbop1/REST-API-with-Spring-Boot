package ogenblad.example.individuellUppgift.mapper;

import jakarta.validation.Valid;
import ogenblad.example.individuellUppgift.dto.MemberDto;
import ogenblad.example.individuellUppgift.entity.Address;
import ogenblad.example.individuellUppgift.entity.Member;
import ogenblad.example.individuellUppgift.dto.DietMemberDto;
import org.springframework.stereotype.Component;


@Component
public class Mapper {

    public Mapper() {}

    public DietMemberDto toDietMembertDto(Member member) {
        return new DietMemberDto(member.getFirstName(), member.getLastName(), member.getAddress(), member.getEmail(), member.getPhone());
    }

    public Member memberDtoToMember(MemberDto memberDto, Address address) {
        return new Member(memberDto.firstName(), memberDto.lastName(), address, memberDto.email(), memberDto.phone(), memberDto.dateOfBirth());
    }
}
