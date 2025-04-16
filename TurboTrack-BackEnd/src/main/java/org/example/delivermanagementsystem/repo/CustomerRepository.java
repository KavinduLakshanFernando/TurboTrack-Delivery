package org.example.delivermanagementsystem.repo;

import org.example.delivermanagementsystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    List<Customer> findByUser_Uid(UUID userId);
}
