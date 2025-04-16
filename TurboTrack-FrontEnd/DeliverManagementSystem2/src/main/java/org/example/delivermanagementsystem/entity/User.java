package org.example.delivermanagementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uid", columnDefinition = "VARCHAR(36)", unique = true, nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID uid;

    @NotNull(message = "Email is required")
    @Column(nullable = false)
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Password is required")
    @Column(nullable = false)
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotNull(message = "Role is required")
    @Column(nullable = false)
    @Size(min = 2, max = 20, message = "Role must be between 2 and 20 characters")
    private String role;

    @NotNull(message = "Phone is required")
    @Column(nullable = false)
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Phone number must be valid")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusProfile Status;

    @PrePersist
    public void prePersist(){
        if (Status == null){
            Status = StatusProfile.ACTIVATE;
        }
    }
}
