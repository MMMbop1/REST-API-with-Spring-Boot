package ogenblad.example.individuellUppgift.security;

import jakarta.persistence.*;
import ogenblad.example.individuellUppgift.entity.Member;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

import java.util.Set;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new TreeSet<>();

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public AppUser() {}

    public AppUser(String username, String password, Set<Role> roles, Member member) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.member = member;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        return getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .toList();
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
