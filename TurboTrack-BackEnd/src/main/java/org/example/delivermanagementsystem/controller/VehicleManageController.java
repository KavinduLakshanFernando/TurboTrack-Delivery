package org.example.delivermanagementsystem.controller;

import jakarta.validation.Valid;
import org.example.delivermanagementsystem.dto.ResponseDTO;
import org.example.delivermanagementsystem.dto.VehicleDTO;
import org.example.delivermanagementsystem.service.VehicleService;
import org.example.delivermanagementsystem.utill.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:63342/")
@RestController
@RequestMapping("api/v1/vehicle")
public class VehicleManageController {

    private final VehicleService vehicleService;

    public VehicleManageController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PreAuthorize("hasAnyAuthority('DRIVER')")
    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> saveVehicle(@RequestBody  VehicleDTO vehicleDTO) {
        System.out.println(vehicleDTO);
        try {
            int res = vehicleService.saveVehicle(vehicleDTO);
            switch (res) {
                case VarList.Created -> {
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseDTO(VarList.Created, "Success", vehicleDTO));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @GetMapping("/allVehicles")
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        try {
            List<VehicleDTO> vehicles = vehicleService.getAllVehicles();
            if (vehicles.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(vehicles);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
