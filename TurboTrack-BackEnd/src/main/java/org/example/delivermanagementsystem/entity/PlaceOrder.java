package org.example.delivermanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PlaceOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "VARCHAR(36)", unique = true, nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID OID;
    private String pickupPoint;
    private String deliveryAddress;
    private double distance;
    private String vehicleType;
    private String deliveryDate;
    private double deliveryAmount;
    private String status;
    private String priority;
    private String receiverContact;

    @ManyToOne
    private Customer customer;


}
