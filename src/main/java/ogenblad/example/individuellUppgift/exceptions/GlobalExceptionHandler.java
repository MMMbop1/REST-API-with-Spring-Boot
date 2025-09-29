package ogenblad.example.individuellUppgift.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String validationErrors(MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach((val) ->
                        errors.append(val.getDefaultMessage()).append("\n")
                );

        return errors.toString();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DateOfBirthExists.class)
    public String dateOfBirthAlreadyExists(DateOfBirthExists ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(AddressNotFoundException.class)
    public String addressDoesNotExist(AddressNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(MemberNotFoundException.class)
    public String memberDoesNotExist(MemberNotFoundException ex) {
        return ex.getMessage();
    }
}
