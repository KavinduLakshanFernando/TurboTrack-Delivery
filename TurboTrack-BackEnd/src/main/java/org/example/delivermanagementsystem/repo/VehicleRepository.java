package org.example.delivermanagementsystem.repo;

import org.example.delivermanagementsystem.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VehicleRepository extends JpaRepository<Vehicle , UUID> {
}
