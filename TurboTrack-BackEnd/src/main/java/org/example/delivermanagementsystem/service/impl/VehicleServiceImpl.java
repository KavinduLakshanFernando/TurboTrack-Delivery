package org.example.delivermanagementsystem.service.impl;

import org.example.delivermanagementsystem.dto.VehicleDTO;
import org.example.delivermanagementsystem.entity.Driver;
import org.example.delivermanagementsystem.entity.Vehicle;
import org.example.delivermanagementsystem.repo.DriverRepository;
import org.example.delivermanagementsystem.repo.VehicleRepository;
import org.example.delivermanagementsystem.service.VehicleService;
import org.example.delivermanagementsystem.utill.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VehicleRepository vehicleRepository;
    @Override
    public int saveVehicle(VehicleDTO vehicleDTO) {
        if (vehicleDTO.getVid() != null && vehicleRepository.existsById(vehicleDTO.getVid())) {
            return VarList.Not_Acceptable;
        } else {
            vehicleRepository.save(modelMapper.map(vehicleDTO, Vehicle.class));
            return VarList.Created;
        }
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return modelMapper.map(vehicles, new TypeToken<List<VehicleDTO>>() {}.getType());
    }
}
