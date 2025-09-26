package ogenblad.example.individuellUppgift.service;

import ogenblad.example.individuellUppgift.entity.Address;

import java.util.Optional;

public interface ServiceAddress {

    Optional<Address> find(Long id);
}
