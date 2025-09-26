package ogenblad.example.individuellUppgift.service;

import ogenblad.example.individuellUppgift.entity.Address;
import ogenblad.example.individuellUppgift.repository.DaoAddress;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceAddressImpl implements ServiceAddress {

    private final DaoAddress daoAddress;

    public ServiceAddressImpl(DaoAddress daoAddress) {
        this.daoAddress = daoAddress;
    }

    public Optional<Address> find(Long id) {
        return daoAddress.find(id);
    }
}
