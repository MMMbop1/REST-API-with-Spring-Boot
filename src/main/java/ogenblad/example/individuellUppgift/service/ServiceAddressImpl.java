package ogenblad.example.individuellUppgift.service;

import ogenblad.example.individuellUppgift.entity.Address;
import ogenblad.example.individuellUppgift.exceptions.AddressNotFoundException;
import ogenblad.example.individuellUppgift.repository.DaoAddress;
import org.springframework.stereotype.Service;

@Service
public class ServiceAddressImpl implements ServiceAddress {

    private final DaoAddress daoAddress;

    public ServiceAddressImpl(DaoAddress daoAddress) {
        this.daoAddress = daoAddress;
    }

    public Address find(Long id) {
        return daoAddress.find(id).orElseThrow(() -> new AddressNotFoundException(id));
    }
}
