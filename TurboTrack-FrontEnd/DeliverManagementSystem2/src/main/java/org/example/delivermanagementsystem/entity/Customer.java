package org.example.delivermanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cid", columnDefinition = "VARCHAR(36)", unique = true, nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID cid;

    private String fname;


    private String lname;

    private String dateOfBirth;

    private String gender;

    private String streetAddress;

    private String city;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_id",referencedColumnName = "uid")
    private User user;


    @OneToMany(mappedBy = "customer" ,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PlaceOrder> orders;
    


}
