package com.example.ecommerceappdemo.dto;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Data
public class ProductDTO {

    private int id;
    private int categoryId;
    private String name;
    private int price;
    private int weight;
    private String description;
    private String imageName;
}
