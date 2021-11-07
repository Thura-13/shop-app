package com.example.ecommerceappdemo.entities;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "First Name cannot be empty")
    private String firstName;

    @NotNull
    @NotBlank(message = "Last Name cannot be empty")
    private String lastName;

    @NotBlank(message = "Enter your email")
    private String email;

    @NotBlank(message = "Enter your password")
    @Length(min = 6,message = "Password must be 6 character")
    private String password;

    @NotBlank(message = "Confirm your password")
    private String confirmPassword;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Roles> roles = new ArrayList<>();

    public User(){

    }
}
