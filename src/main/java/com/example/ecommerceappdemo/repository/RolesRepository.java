package com.example.ecommerceappdemo.repository;

import com.example.ecommerceappdemo.entities.Roles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends CrudRepository<Roles,Integer> {

    Optional<Roles> findById(int id);
}
