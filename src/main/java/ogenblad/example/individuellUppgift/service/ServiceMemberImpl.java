package ogenblad.example.individuellUppgift.service;

import ogenblad.example.individuellUppgift.entity.Member;
import ogenblad.example.individuellUppgift.exceptions.MemberNotFoundException;
import ogenblad.example.individuellUppgift.repository.DaoMember;
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

        if (member.getAddress().getId() != null) {
            serviceAddress.find(member.getAddress().getId());
        }

        return memberDao.update(member);
    }

    @Override
    public Member patchUpdate(Member member) {
        return null;
    }
}
