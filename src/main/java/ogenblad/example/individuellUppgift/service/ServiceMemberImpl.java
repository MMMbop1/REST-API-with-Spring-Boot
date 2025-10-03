package ogenblad.example.individuellUppgift.service;

import jakarta.transaction.Transactional;
import ogenblad.example.individuellUppgift.dto.DietMemberDto;
import ogenblad.example.individuellUppgift.dto.RequestMemberDto;
import ogenblad.example.individuellUppgift.dto.PatchMemberDto;
import ogenblad.example.individuellUppgift.dto.ResponseMemberDto;
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
    public ResponseMemberDto find(Long id) {
        Member member = memberDao.find(id).orElseThrow(() -> new MemberNotFoundException(id));
        ResponseMemberDto responseMemberDto = mapper.toResponseMemberDto(member);

        return responseMemberDto;
    }

    @Override
    public List<ResponseMemberDto> findAll() {
        List<ResponseMemberDto> memberDtos = new ArrayList<>();
        memberDao.findAll().forEach((member) -> memberDtos.add(mapper.toResponseMemberDto(member)));

        return memberDtos;
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
    @Transactional
    public Member update(RequestMemberDto requestMemberDto, Long id) {
        Member member = memberDao.find(id).orElseThrow(() -> new MemberNotFoundException(id));

        Address address = serviceAddress.find(requestMemberDto.address());

        Member updateMember = mapper.memberDtoToMember(requestMemberDto, address);
        updateMember.setId(id);

        return memberDao.update(updateMember);
    }

    @Override
    @Transactional
    public Member patchUpdate(PatchMemberDto patchMemberDto, Long id) {
        Member member = memberDao.find(id).orElseThrow(() -> new MemberNotFoundException(id));

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
    @Transactional
    public Member save(Member postMember) {
        return memberDao.save(postMember);
    }

    @Override
    @Transactional
    public List<Member> saveAll(List<Member> members) {
        return memberDao.saveAll(members);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Member member = memberDao.find(id).orElseThrow(() -> new MemberNotFoundException(id));
        memberDao.delete(member);
    }
}
