package org.example.delivermanagementsystem.repo;

import org.example.delivermanagementsystem.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DriverRepository extends JpaRepository<Driver, UUID> {
    List<Driver> findByUser_Uid(UUID id);
}
