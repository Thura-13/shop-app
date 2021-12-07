package com.example.ecommerceappdemo.service;

import com.example.ecommerceappdemo.entities.Category;
import com.example.ecommerceappdemo.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Iterable<Category> getAllCategory() {
            return repository.findAll();
    }

    public void saveCategory(Category category){
        repository.save(category);
    }

    public void deleteCategory(int id){
        repository.deleteById(id);
    }

    public Category getCategoryById(int id) {
        Optional<Category> categoryOptional = repository.findById(id);
        Category category = null;
        if(categoryOptional.isPresent()) {
            category = categoryOptional.get();
        }else {
            throw new RuntimeException("Not Found");
        }

        return category;
    }
}
