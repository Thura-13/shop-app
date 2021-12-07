package com.example.ecommerceappdemo.repository;

import com.example.ecommerceappdemo.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Integer> {

}
