package ogenblad.example.individuellUppgift.exceptions;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(Long id) {
        super("Member with id: " + id + " not found.");
    }
}
