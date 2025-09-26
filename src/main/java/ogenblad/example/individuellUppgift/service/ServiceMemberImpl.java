package ogenblad.example.individuellUppgift.service;

import ogenblad.example.individuellUppgift.entity.Member;
import ogenblad.example.individuellUppgift.repository.DaoMember;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceMemberImpl implements ServiceMember {

    private DaoMember memberDao;

    public ServiceMemberImpl(DaoMember memberDao) {
       this.memberDao = memberDao;
    }

    @Override
    public Optional<Member> find(Long id) {
        return memberDao.find(id);
    }

    @Override
    public List<Member> findAll() {
        return memberDao.findAll();
    }

    @Override
    public Member update(Member member) {
        return memberDao.update(member);
    }
}
