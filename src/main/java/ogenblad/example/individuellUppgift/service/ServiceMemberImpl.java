package ogenblad.example.individuellUppgift.service;

import ogenblad.example.individuellUppgift.dto.DietMemberDto;
import ogenblad.example.individuellUppgift.dto.MemberDto;
import ogenblad.example.individuellUppgift.dto.PatchMemberDto;
import ogenblad.example.individuellUppgift.entity.Address;
import ogenblad.example.individuellUppgift.entity.Member;
import ogenblad.example.individuellUppgift.exceptions.MemberNotFoundException;
import ogenblad.example.individuellUppgift.mapper.Mapper;
import ogenblad.example.individuellUppgift.repository.DaoMember;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceMemberImpl implements ServiceMember {

    private final DaoMember memberDao;
    private final ServiceAddress serviceAddress;
    private final Mapper mapper;

    public ServiceMemberImpl(DaoMember memberDao, ServiceAddress serviceAddress, Mapper mapper) {
       this.memberDao = memberDao;
       this.serviceAddress = serviceAddress;
       this.mapper = mapper;
    }

    @Override
    public Member find(Long id) {
        return memberDao.find(id).orElseThrow(() -> new MemberNotFoundException(id));
    }

    @Override
    public List<Member> findAll() {
        return memberDao.findAll();
    }

    @Override
    public List<DietMemberDto> findAllDietMembers() {
        List<DietMemberDto> memberDtos = new ArrayList<>();

        memberDao.findAll().forEach(member -> {
            memberDtos.add(mapper.toDietMembertDto(member));
        });

        return memberDtos;
    }

    @Override
    public Member update(MemberDto memberDto, Long id) {
        Member member = find(id);

        Address address = serviceAddress.find(memberDto.address());

        Member updateMember = mapper.memberDtoToMember(memberDto, address);
        updateMember.setId(id);

        return memberDao.update(updateMember);
    }

    @Override
    public Member patchUpdate(PatchMemberDto patchMemberDto, Long id) {
        Member member = find(id);

        if (patchMemberDto.firstName() != null) {
            member.setFirstName(patchMemberDto.firstName());
        }

        if (patchMemberDto.lastName() != null) {
            member.setLastName(patchMemberDto.lastName());
        }

        if (patchMemberDto.addressId() != null) {
            Address address = serviceAddress.find(patchMemberDto.addressId());
            member.setAddress(address);
        }

        if (patchMemberDto.email() != null) {
            member.setEmail(patchMemberDto.email());
        }

        if (patchMemberDto.phone() != null) {
            member.setPhone(patchMemberDto.phone());
        }

        if (patchMemberDto.dateOfBirth() != null) {
            member.setDateOfBirth(patchMemberDto.dateOfBirth());
        }

        return memberDao.update(member);
    }

    @Override
    public Member save(Member postMember) {
        return memberDao.save(postMember);
    }

    @Override
    public void delete(Long id) {
        memberDao.delete(find(id));
    }
}
