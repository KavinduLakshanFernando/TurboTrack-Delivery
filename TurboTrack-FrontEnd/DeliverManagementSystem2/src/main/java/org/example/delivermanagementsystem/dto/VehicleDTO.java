package org.example.delivermanagementsystem.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lombok.*;
import org.example.delivermanagementsystem.entity.Driver;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {
    private UUID vid;

    private String vehicleType;

    private String vehicleModel;

    private int vehicleYear;

    private String vehicleColor;


    private String licensePlate;

    private LocalDate insuranceExpiry;

    private Driver driver;
}

