package org.example.delivermanagementsystem.dto;

import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.delivermanagementsystem.entity.User;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverDTO {

    private UUID DID;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50)
    private String lastName;

//    @NotNull(message = "Date of birth is required")
    private String dateOfBirth;

    private String gender;

    @NotBlank(message = "Street address is required")
    @Size(min = 5, max = 100)
    private String streetAddress;

    @NotBlank(message = "City is required")
    @Size(min = 2, max = 50)
    private String city;

    @NotBlank(message = "License number is required")
    @Size(min = 5, max = 20)
    private String licenseNumber;

    @NotBlank(message = "License state is required")
    @Size(min = 2, max = 50)
    private String licenseState;

    @NotNull(message = "License expiry date is required")
    private String licenseExpiry;

    @NotBlank(message = "License class is required")
    private String licenseClass;

    @NotBlank(message = "Experience is required")
    private String experience;

    @NotBlank(message = "Availability is required")
    private String availability;

    private String preferredArea;

    private User user;
}
