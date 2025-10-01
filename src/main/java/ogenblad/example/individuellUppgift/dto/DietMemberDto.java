package ogenblad.example.individuellUppgift.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import ogenblad.example.individuellUppgift.entity.Address;

public record DietMemberDto(
        @Size(min = 2, max = 100,
                message = "Minimum length for name is 2 characters and maximum 100.")
        String firstName,

        @Size(min = 2, max = 100,
                message = "Minimum length for name is 2 characters and maximum 100.")
        String lastName,

        Address address,

        @Pattern(regexp = "\\w|.{1,50}@\\w{1,20}\\.\\w{1,10}",
                message = "Email must match the pattern: local part (1–50 chars) + '@' + domain (1–20 chars) + top-level domain.")
        String email,

        @Size(max = 15,
                message = "maximum length is 15 characters")
        String phone
) {}
