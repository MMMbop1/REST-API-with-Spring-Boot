package ogenblad.example.individuellUppgift.exceptions;

public class AddressNotFoundException extends RuntimeException{
    public AddressNotFoundException(Long id) {
        super("Address with id: " + id + " not found.");
    }
}
