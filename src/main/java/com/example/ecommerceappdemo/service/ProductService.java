package com.example.ecommerceappdemo.service;

import com.example.ecommerceappdemo.entities.Product;
import com.example.ecommerceappdemo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Iterable<Product> findAllProduct(){
        return repository.findAll();
    }

    public void addProduct(Product product) {
        repository.save(product);
    }

    public void deleteProduct(int id){
        repository.deleteById(id);
    }

    public Product findProductById(int id) {

        Optional<Product> optionalProduct = repository.findById(id);
        Product product = null;

        if(optionalProduct.isPresent()) {
            product = optionalProduct.get();
        }else {
            throw new RuntimeException("Not Found");
        }
        return product;
    }

    public List<Product> getAllProductByCategoryId(int id){
        return repository.findAllByCategory_Id(id);
    }
}
