package org.example.delivermanagementsystem.service.impl;

import org.example.delivermanagementsystem.dto.DriverDTO;
import org.example.delivermanagementsystem.dto.UserDTO;
import org.example.delivermanagementsystem.entity.Driver;
import org.example.delivermanagementsystem.entity.User;
import org.example.delivermanagementsystem.repo.DriverRepository;
import org.example.delivermanagementsystem.repo.UserRepository;
import org.example.delivermanagementsystem.service.DriverService;
import org.example.delivermanagementsystem.utill.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class DriverServiceImpl implements DriverService {
    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public int saveDriver(DriverDTO driverDTO) {
        if (driverDTO.getDID() != null && driverRepository.existsById(driverDTO.getDID())) {
            return VarList.Not_Acceptable;
        } else {
            driverRepository.save(modelMapper.map(driverDTO, Driver.class));
            return VarList.Created;
        }
    }

    @Override
    public List<DriverDTO> getAllDrivers() {
        List<Driver> drivers = driverRepository.findAll();
        return drivers.stream()
                .map(driver -> modelMapper.map(driver, DriverDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public int deleteDriver(UUID id) {
        driverRepository.deleteById(id);
        return VarList.OK;
    }

    @Override
    public List<DriverDTO> getDriversByUserId(UUID id) {
        List<Driver> drivers = driverRepository.findByUser_Uid(id); // Make sure method name matches in repo

        if (drivers.isEmpty()) {
            return Collections.emptyList(); // Use type-safe empty list
        }

        return drivers.stream()
                .map(driver -> modelMapper.map(driver, DriverDTO.class))
                .collect(Collectors.toList());
    }



}

