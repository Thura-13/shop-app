package com.example.ecommerceappdemo.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,unique = true)
    private String roleName;

    public Roles(String roleName) {
        this.roleName = roleName;
    }

//    @ManyToMany(mappedBy = "roles")
//    private List<User> user;

    @ManyToOne
    private User user;

    public Roles(){

    }

}
