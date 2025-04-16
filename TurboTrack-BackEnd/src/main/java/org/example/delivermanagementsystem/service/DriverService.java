package org.example.delivermanagementsystem.service;

import jakarta.validation.Valid;
import org.example.delivermanagementsystem.dto.DriverDTO;

import java.util.List;
import java.util.UUID;

public interface DriverService {
    int saveDriver(@Valid DriverDTO driverDTO);

    List<DriverDTO> getAllDrivers();

    int deleteDriver(UUID id);

    List<DriverDTO> getDriversByUserId(UUID id);
}
