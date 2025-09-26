package ogenblad.example.individuellUppgift.service;

import ogenblad.example.individuellUppgift.entity.Member;

import java.util.*;

public interface ServiceMember {

    List<Member> findAll();

    Optional<Member> find(Long id);

    Member update(Member member);
}
