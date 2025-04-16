package org.example.delivermanagementsystem.service;

import jakarta.validation.Valid;
import org.example.delivermanagementsystem.dto.CustomerDTO;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    int saveCustomer(@Valid CustomerDTO customerDTO);


    List<CustomerDTO> getAllCustomers();

    List<CustomerDTO> getCustomersByUserId(UUID userId);

    int deleteCustomer(UUID cid);
}
