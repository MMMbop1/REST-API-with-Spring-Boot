package org.ogenblad.spring.rest.exceptions;

public class AddressNotFoundException extends RuntimeException{
    public AddressNotFoundException(Long id) {
        super("Address with id: " + id + " not found.");
    }
}
