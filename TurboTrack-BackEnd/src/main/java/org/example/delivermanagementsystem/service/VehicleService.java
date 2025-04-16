package org.example.delivermanagementsystem.service;

import jakarta.validation.Valid;
import org.example.delivermanagementsystem.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {
    int saveVehicle( VehicleDTO vehicleDTO);

    List<VehicleDTO> getAllVehicles();
}
