package org.ogenblad.spring.rest.repository;

import org.ogenblad.spring.rest.entity.Address;

import java.util.Optional;

public interface DaoAddress {

    Optional<Address> find(Long id);
}
