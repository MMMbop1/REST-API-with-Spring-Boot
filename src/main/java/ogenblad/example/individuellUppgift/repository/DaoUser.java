package ogenblad.example.individuellUppgift.repository;

import ogenblad.example.individuellUppgift.security.AppUser;

import java.util.Optional;

public interface DaoUser {
    Optional<AppUser> findByUsername(String username);

}
