package ogenblad.example.individuellUppgift.service;

import ogenblad.example.individuellUppgift.entity.Member;
import ogenblad.example.individuellUppgift.repository.MemberDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceMemberImpl implements ServiceMember {

    private MemberDao memberDao;

    public ServiceMemberImpl(MemberDao memberDao) {
       this.memberDao = memberDao;
    }


    @Override
    public List<Member> findAll() {
        return memberDao.findAll();
    }
}
