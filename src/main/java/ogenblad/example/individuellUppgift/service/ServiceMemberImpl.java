package ogenblad.example.individuellUppgift.service;

import ogenblad.example.individuellUppgift.entity.Address;
import ogenblad.example.individuellUppgift.entity.Member;
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

        // titta på den här, addressen får ej vara null.
        
        if (member.getAddress() != null && member.getAddress().getId() != null) {
            member.setAddress(serviceAddress.find(member.getAddress().getId()));
        }

        return memberDao.update(member);
    }

    @Override
    public Member patchUpdate(Member patchMember, Long id) {
        Member member = find(id);

        if (patchMember.getFirstName() != null) {
            member.setFirstName(patchMember.getFirstName());
        }

        if (patchMember.getLastName() != null) {
            member.setLastName(patchMember.getLastName());
        }

        if (patchMember.getAddress() != null && patchMember.getAddress().getId() != null) {
            Address address = serviceAddress.find(patchMember.getAddress().getId());
            member.setAddress(address);
        }

        if (patchMember.getEmail() != null) {
            member.setEmail(patchMember.getEmail());
        }

        if (patchMember.getPhone() != null) {
            member.setPhone(patchMember.getPhone());
        }


        if (patchMember.getDateOfBirth() != null) {
            member.setDateOfBirth(patchMember.getDateOfBirth());
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
