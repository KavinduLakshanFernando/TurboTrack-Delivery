package org.example.delivermanagementsystem.repo;

import org.example.delivermanagementsystem.entity.PlaceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlaceOrderRepository extends JpaRepository<PlaceOrder, UUID> {
    List<PlaceOrder> findAllByStatus(String pending);


        List <PlaceOrder> findByCustomer_cid(UUID cid);
}
