package org.ogenblad.spring.rest.exceptions;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(Long id) {
        super("Member with id: " + id + " not found.");
    }
}
