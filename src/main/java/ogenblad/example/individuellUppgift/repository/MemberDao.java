package ogenblad.example.individuellUppgift.repository;

import ogenblad.example.individuellUppgift.entity.Member;
import java.util.List;

public interface MemberDao {

    Member save(Member member);

    List<Member> saveAll(List<Member> members);
}
