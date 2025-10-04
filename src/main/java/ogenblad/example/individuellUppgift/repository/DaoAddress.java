package ogenblad.example.individuellUppgift.repository;

import ogenblad.example.individuellUppgift.entity.Address;

import java.util.Optional;

public interface DaoAddress {

    Optional<Address> find(Long id);
}
