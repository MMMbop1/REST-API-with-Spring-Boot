package ogenblad.example.individuellUppgift.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PatchMemberDto (

    @Size(min = 2, max = 100,
            message = "Minimum length for name is 2 characters and maximum 100.")
    String firstName,

    @Size(min = 2, max = 100,
            message = "Minimum length for name is 2 characters and maximum 100.")
    String lastName,

    Long addressId,

    @Pattern(regexp = "\\w|.{1,50}@\\w{1,20}\\.\\w{1,10}",
            message = "Email must match the pattern: local part (1–50 chars) + '@' + domain (1–20 chars) + top-level domain.")
    String email,

    @Size(max = 15,
            message = "maximum length is 15 characters")
    String phone,

    @Size(min = 12, max = 12,
            message = "must be 12 characters length in format YYYYMMDDXXXX")
    @Pattern(regexp = "\\d{12}", message = "Accepted format for dateOfBirth YYYYMMDDXXXX")
    String dateOfBirth)
{}
