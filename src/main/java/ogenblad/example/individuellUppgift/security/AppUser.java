package ogenblad.example.individuellUppgift.security;

import jakarta.persistence.Entity;
import ogenblad.example.individuellUppgift.entity.Member;

import java.util.Set;

@Entity
public class AppUser {

    private String username;

    private String password;

    private Set<Role> roles;

    private Member member;


}
