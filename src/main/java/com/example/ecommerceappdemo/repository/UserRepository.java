package com.example.ecommerceappdemo.repository;

import com.example.ecommerceappdemo.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    Optional<User> findUserByEmail(String email);
}
