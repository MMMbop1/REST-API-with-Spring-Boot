package ogenblad.example.individuellUppgift.repository;

import ogenblad.example.individuellUppgift.entity.Address;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface DaoAddress {

    Optional<Address> find(Long id);
}
