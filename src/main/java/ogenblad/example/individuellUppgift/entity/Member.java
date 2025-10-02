package ogenblad.example.individuellUppgift.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import ogenblad.example.individuellUppgift.security.AppUser;
import ogenblad.example.individuellUppgift.security.Role;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 100,
            message = "Minimum length for name is 2 characters and maximum 100.")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 100,
            message = "Minimum length for name is 2 characters and maximum 100.")
    private String lastName;

    @NotNull(message = "Address can not be empty")
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="address_id")
    private Address address;

    @NotBlank
    @Pattern(regexp = "\\w|.{1,50}@\\w{1,20}\\.\\w{1,10}",
            message = "Email must match the pattern: local part (1–50 chars) + '@' + domain (1–20 chars) + top-level domain.")
    private String email;

    @Size(max = 15,
            message = "maximum length is 15 characters")
    private String phone;

    @Size(min = 12, max = 12,
            message = "must be 12 characters length in format YYYYMMDDXXXX")
    @Pattern(regexp = "\\d{12}", message = "Accepted format for dateOfBirth YYYYMMDDXXXX")
    @Column(unique = true, nullable = false)
    private String dateOfBirth;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private AppUser appUser;

    /** protected? */
    public Member() {}

    public Member(String firstName, String lastName, Address address, String email, String phone, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    /** private? */
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
