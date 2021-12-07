package com.example.ecommerceappdemo.repository;

import com.example.ecommerceappdemo.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product,Integer> {

    List<Product> findAllByCategory_Id(int id);
    List<Product> findAllByProductName(String keyword);
}
