package org.example.delivermanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.delivermanagementsystem.entity.Customer;
import org.example.delivermanagementsystem.entity.Vehicle;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderDTO {
    private UUID OID;
    private String pickupPoint;
    private String deliveryAddress;
    private double distance;
    private String vehicleType;
    private String deliveryDate;
    private double deliveryAmount;
    private String status;
    private String priority;
    private Customer customer;
    private String receiverContact;
}
