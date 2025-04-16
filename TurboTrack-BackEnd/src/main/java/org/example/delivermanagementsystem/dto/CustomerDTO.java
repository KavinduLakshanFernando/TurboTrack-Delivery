package org.example.delivermanagementsystem.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.delivermanagementsystem.entity.PlaceOrder;
import org.example.delivermanagementsystem.entity.User;

import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO{
    private UUID Cid;

    private String fname;

    private String lname;

    private String dateOfBirth;

    private String gender;

    private String streetAddress;

    private String city;

    private User user;

//    private List<PlaceOrder> orders;
}
