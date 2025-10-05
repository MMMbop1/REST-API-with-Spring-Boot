package org.ogenblad.spring.rest.service;

import org.ogenblad.spring.rest.entity.Address;
import org.ogenblad.spring.rest.exceptions.AddressNotFoundException;
import org.ogenblad.spring.rest.repository.DaoAddress;
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
