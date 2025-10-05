package org.ogenblad.spring.rest.repository;

import org.ogenblad.spring.rest.security.AppUser;

import java.util.Optional;

public interface DaoUser {
    Optional<AppUser> findByUsername(String username);

}
