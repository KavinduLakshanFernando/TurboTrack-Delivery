package org.example.delivermanagementsystem.controller;

import jakarta.validation.Valid;
import org.example.delivermanagementsystem.dto.DriverDTO;
import org.example.delivermanagementsystem.dto.ResponseDTO;
import org.example.delivermanagementsystem.repo.DriverRepository;
import org.example.delivermanagementsystem.service.DriverService;
import org.example.delivermanagementsystem.utill.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:63342/")
@RestController
@RequestMapping("api/v1/driver")
public class DriverManageController {

    private final DriverService driverService;

    public DriverManageController(DriverService driverService) {
        this.driverService = driverService;
    }
    @PreAuthorize("hasAnyAuthority('DRIVER')")
    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> saveDriver(@RequestBody @Valid DriverDTO driverDTO) {
        try {
            int res = driverService.saveDriver(driverDTO);
            switch (res) {
                case VarList.Created -> {
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseDTO(VarList.Created, "Success", driverDTO));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), driverDTO));
        }
        }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/allDrivers")
    public ResponseEntity<List<DriverDTO>> getAllDrivers() {
        try {
            List<DriverDTO> drivers = driverService.getAllDrivers();
            if (drivers.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(drivers);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteDriver(@PathVariable UUID id) {
        try {
            int res = driverService.deleteDriver(id);
            switch (res) {
                case VarList.OK -> {
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseDTO(VarList.OK, "Success", id));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", id));
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), id));
        }
    }

    @GetMapping("/getPersonalData/{userId}")
    public ResponseEntity<List<DriverDTO>> getPersonalDataByUserId(@PathVariable UUID userId) {
        List<DriverDTO> driversByUserId = driverService.getDriversByUserId(userId);
        if (driversByUserId.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(driversByUserId);
    }

}