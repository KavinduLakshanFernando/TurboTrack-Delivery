package org.example.delivermanagementsystem.service;

import jakarta.validation.Valid;
import org.example.delivermanagementsystem.dto.PlaceOrderDTO;

import java.util.List;
import java.util.UUID;

public interface PlaceOrderService {
    int placeOrder(@Valid PlaceOrderDTO placeOrderDTO);

    List<PlaceOrderDTO> getPendingOrders();

    void confirmOrder(UUID id);

    List<PlaceOrderDTO> getAllOrders();

    List<PlaceOrderDTO> getOrdersByCustomerId(UUID cid);
}
