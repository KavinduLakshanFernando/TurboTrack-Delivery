package org.example.delivermanagementsystem.service.impl;

import org.example.delivermanagementsystem.dto.CustomerDTO;
import org.example.delivermanagementsystem.dto.DriverDTO;
import org.example.delivermanagementsystem.entity.Customer;
import org.example.delivermanagementsystem.entity.Driver;
import org.example.delivermanagementsystem.repo.CustomerRepository;
import org.example.delivermanagementsystem.repo.UserRepository;
import org.example.delivermanagementsystem.service.CustomerService;
import org.example.delivermanagementsystem.utill.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;


    @Override

    public int saveCustomer(CustomerDTO customerDTO) {
        if (customerDTO.getCid() != null && customerRepository.existsById(customerDTO.getCid())) {
            return VarList.Not_Acceptable;
        } else {
            userRepository.findById(customerDTO.getUser().getUid())
                            .orElseThrow(() -> new RuntimeException("User not found"));
            customerRepository.save(modelMapper.map(customerDTO, Customer.class));
            return VarList.Created;
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> getCustomersByUserId(UUID userId) {
        List<Customer> customers = customerRepository.findByUser_Uid(userId);
        return customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public int deleteCustomer(UUID cid) {
        customerRepository.deleteById(cid);
        return VarList.OK;
    }
}
