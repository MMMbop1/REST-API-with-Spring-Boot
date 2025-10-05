package org.ogenblad.spring.rest.exceptions;

public class DateOfBirthExists extends RuntimeException {
    public DateOfBirthExists(String dateOfBirth) {
        super("Date of Birth is not unique and already exists: " + dateOfBirth);
    }
}
