package ogenblad.example.individuellUppgift.service;

import jakarta.transaction.Transactional;
import ogenblad.example.individuellUppgift.dto.DietMemberDto;
import ogenblad.example.individuellUppgift.dto.RequestMemberDto;
import ogenblad.example.individuellUppgift.dto.PatchMemberDto;
import ogenblad.example.individuellUppgift.dto.ResponseMemberDto;
import ogenblad.example.individuellUppgift.entity.Address;
import ogenblad.example.individuellUppgift.entity.Member;
import ogenblad.example.individuellUppgift.exceptions.DateOfBirthExists;
import ogenblad.example.individuellUppgift.exceptions.MemberNotFoundException;
import ogenblad.example.individuellUppgift.mapper.Mapper;
import ogenblad.example.individuellUppgift.repository.DaoMember;
import ogenblad.example.individuellUppgift.repository.DaoUser;
import ogenblad.example.individuellUppgift.security.AppUser;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ServiceMemberImpl implements ServiceMember {

    private final DaoMember memberDao;
    private final ServiceAddress serviceAddress;
    private final DaoUser userDao;

    public ServiceMemberImpl(DaoMember memberDao, ServiceAddress serviceAddress, DaoUser userDao) {
       this.memberDao = memberDao;
       this.serviceAddress = serviceAddress;
       this.userDao = userDao;
    }

    @Override
    public ResponseMemberDto find(Long id) {
        Member member = memberDao.find(id).orElseThrow(() -> new MemberNotFoundException(id));
        return Mapper.toResponseMemberDto(member);
    }

    @Override
    public List<ResponseMemberDto> findAll() {
        return memberDao.findAll()
                .stream()
                .map(Mapper::toResponseMemberDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DietMemberDto> findAllDietMembers() {
        return memberDao.findAll()
                .stream()
                .map(Mapper::toDietMemberDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ResponseMemberDto update(RequestMemberDto requestMemberDto, Long id) {
        Member member = memberDao.find(id).orElseThrow(() -> new MemberNotFoundException(id));
        Address address = serviceAddress.find(requestMemberDto.address());
        AppUser appUser = member.getAppUser();

        dateOfBirthIsUnique(requestMemberDto.dateOfBirth(), member.getId());

        Member updateMember = Mapper.memberDtoToMember(requestMemberDto, address, appUser);

        updateMember.setId(id);

        return Mapper.toResponseMemberDto(memberDao.update(updateMember));
    }

    @Override
    @Transactional
    public ResponseMemberDto update(RequestMemberDto requestMemberDto, Long id, Principal principal) {
        Member member = memberDao.find(id).orElseThrow(() -> new MemberNotFoundException(id));

        AppUser appUser = userDao.findByUsername(principal.getName()).orElseThrow(() -> {
            throw new IllegalArgumentException("No user with that username");
        });

        if (!member.getId().equals(appUser.getMember().getId())) {
            throw new IllegalArgumentException("You are not allowed to update other users");
        }

        dateOfBirthIsUnique(requestMemberDto.dateOfBirth(), member.getId());

        Address address = serviceAddress.find(requestMemberDto.address());
        Member updateMember = Mapper.memberDtoToMember(requestMemberDto, address);
        updateMember.setId(id);

        return Mapper.toResponseMemberDto(memberDao.update(updateMember));
    }

    @Override
    @Transactional
    public ResponseMemberDto patchUpdate(PatchMemberDto patchMemberDto, Long id) {
        Member member = memberDao.find(id).orElseThrow(() -> new MemberNotFoundException(id));

        if (patchMemberDto.firstName() != null) {
            member.setFirstName(patchMemberDto.firstName());
        }

        if (patchMemberDto.lastName() != null) {
            member.setLastName(patchMemberDto.lastName());
        }

        if (patchMemberDto.address() != null) {
            Address address = serviceAddress.find(patchMemberDto.address());
            member.setAddress(address);
        }

        if (patchMemberDto.email() != null) {
            member.setEmail(patchMemberDto.email());
        }

        if (patchMemberDto.phone() != null) {
            member.setPhone(patchMemberDto.phone());
        }

        if (patchMemberDto.dateOfBirth() != null) {
            dateOfBirthIsUnique(patchMemberDto.dateOfBirth(), member.getId());
            member.setDateOfBirth(patchMemberDto.dateOfBirth());
        }

        return Mapper.toResponseMemberDto(memberDao.update(member));
    }

    @Override
    @Transactional
    public ResponseMemberDto save(RequestMemberDto requestMemberDto) {
        memberDao.memberByDateOfBirth(requestMemberDto.dateOfBirth()).ifPresent(existingDateOfBirthMember -> {
            throw new DateOfBirthExists(requestMemberDto.dateOfBirth());
        });

        Address address = serviceAddress.find(requestMemberDto.address());
        Member savedMember = Mapper.memberDtoToMember(requestMemberDto, address);

        return Mapper.toResponseMemberDto(memberDao.save(savedMember));
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

    private void dateOfBirthIsUnique(String dateOfBirth, Long idToIgnore) {
        memberDao.memberByDateOfBirth(dateOfBirth)
                .filter(member -> !Objects.equals(member.getId(), idToIgnore))
                .ifPresent(member -> {
                    throw new DateOfBirthExists(dateOfBirth);
                });
    }
}
