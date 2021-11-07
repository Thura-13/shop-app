package com.example.ecommerceappdemo.dto;

import lombok.Data;

import javax.persistence.*;


@Data
public class ProductDTO {

    private int id;
    private int categoryId;
    private String name;
    private double price;
    private double weight;
    private String description;
    private String imageName;
}
