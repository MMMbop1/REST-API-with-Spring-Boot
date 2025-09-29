package ogenblad.example.individuellUppgift.service;

import ogenblad.example.individuellUppgift.dto.MemberDto;
import ogenblad.example.individuellUppgift.entity.Address;
import ogenblad.example.individuellUppgift.entity.Member;
import ogenblad.example.individuellUppgift.exceptions.DateOfBirthExists;
import ogenblad.example.individuellUppgift.exceptions.MemberNotFoundException;
import ogenblad.example.individuellUppgift.repository.DaoMember;
import ogenblad.example.individuellUppgift.repository.DaoMemberImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceMemberImpl implements ServiceMember {

    private DaoMember memberDao;
    private ServiceAddress serviceAddress;

    public ServiceMemberImpl(DaoMember memberDao, ServiceAddress serviceAddress) {
       this.memberDao = memberDao;
       this.serviceAddress = serviceAddress;
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
    public Member update(Member member, Long id) {
        find(id);
        member.setId(id);
        validateIfMemberAlreadyExists(member.getDateOfBirth());

        if (member.getAddress() != null && member.getAddress().getId() != null) {
            member.setAddress(serviceAddress.find(member.getAddress().getId()));
        }

        return memberDao.update(member);
    }

    @Override
    public Member patchUpdate(MemberDto patchMemberDto, Long id) {
        Member member = find(id);

        if (patchMemberDto.firstName() != null) {
            member.setFirstName(patchMemberDto.firstName());
        }

        if (patchMemberDto.lastName() != null) {
            member.setLastName(patchMemberDto.lastName());
        }

        if (patchMemberDto.address() != null && patchMemberDto.address().getId() != null) {
            Address address = serviceAddress.find(patchMemberDto.address().getId());
            member.setAddress(address);
        }

        if (patchMemberDto.email() != null) {
            member.setEmail(patchMemberDto.email());
        }

        if (patchMemberDto.phone() != null) {
            member.setPhone(patchMemberDto.phone());
        }

        if (patchMemberDto.dateOfBirth() != null) {
            validateIfMemberAlreadyExists(patchMemberDto.dateOfBirth());
            member.setDateOfBirth(patchMemberDto.dateOfBirth());
        }

        return memberDao.update(member);
    }

    @Override
    public Member save(Member postMember) {
        validateIfMemberAlreadyExists(postMember.getDateOfBirth());
        return memberDao.save(postMember);
    }

    @Override
    public void delete(Long id) {
        memberDao.delete(find(id));
    }

    private void validateIfMemberAlreadyExists(String dateOfBirth) {
        memberDao.memberByDateOfBirth(dateOfBirth).ifPresent(alreadyExists -> {
            throw new DateOfBirthExists(alreadyExists.getDateOfBirth());
        });
    }
}
