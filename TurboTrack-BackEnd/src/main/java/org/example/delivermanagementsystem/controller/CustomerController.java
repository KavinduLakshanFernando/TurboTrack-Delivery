package org.example.delivermanagementsystem.controller;

import jakarta.validation.Valid;
import org.example.delivermanagementsystem.dto.CustomerDTO;
import org.example.delivermanagementsystem.dto.DriverDTO;
import org.example.delivermanagementsystem.dto.ResponseDTO;
import org.example.delivermanagementsystem.service.CustomerService;
import org.example.delivermanagementsystem.utill.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:63342/")
@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> saveCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        try {
            int res = customerService.saveCustomer(customerDTO);
            switch (res) {
                case VarList.Created -> {
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseDTO(VarList.Created, "Success", customerDTO));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), customerDTO));
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/allCustomers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        try {
            List<CustomerDTO> customers = customerService.getAllCustomers();
            if (customers.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    @GetMapping("/getPersonalData/{userId}")
    public ResponseEntity<List<CustomerDTO>> getPersonalDataByUserId(@PathVariable UUID userId) {
        List<CustomerDTO> customersByUserId = customerService.getCustomersByUserId(userId);
        if (customersByUserId.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(customersByUserId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/delete/{cid}")
    public ResponseEntity<ResponseDTO> deleteCustomer(@PathVariable UUID cid) {
        try {
            int res = customerService.deleteCustomer(cid);
            switch (res) {
                case VarList.OK -> {
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseDTO(VarList.OK, "Success", cid));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", cid));
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), cid));
        }
    }
}
