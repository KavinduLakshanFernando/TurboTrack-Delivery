package org.example.delivermanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "VARCHAR(36)", unique = true, nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID vid;

//    @NotBlank(message = "Vehicle type is required")
    private String vehicleType;

//    @NotBlank(message = "Vehicle model is required")
    private String vehicleModel;
//
//    @Min(value = 1900, message = "Vehicle year must be no earlier than 1900")
//    @Max(value = 2100, message = "Vehicle year must be realistic")
    private int vehicleYear;

//    @NotBlank(message = "Vehicle color is required")
    private String vehicleColor;
//
//    @NotBlank(message = "License plate is required")
//    @Pattern(
//    regexp = "^[A-Z0-9-]{1,10}$"
//    message = "License plate must be alphanumeric and up to 10 characters"
//    )
    private String licensePlate;

//    @Future(message = "Insurance expiry date must be in the future")
    private String insuranceExpiry;

    @ManyToOne
    private Driver driver;




}
