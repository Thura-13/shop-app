package com.example.ecommerceappdemo.controller;

import com.example.ecommerceappdemo.entities.Category;
import com.example.ecommerceappdemo.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController{


    private final CategoryService service;

    public AdminController(CategoryService service) {
        this.service = service;
    }

    @GetMapping("admin")
    public String index(){
        return "admin-home";
    }

    @GetMapping("admin/categories")
    public String getCategories(Model model) {
        model.addAttribute("categories",service.getAllCategory());
        return "categories";
    }

    @GetMapping("admin/category/add")
    public String addCategory(Model model) {
        model.addAttribute("category",new Category());
        return "add-category";
    }

    @PostMapping("admin/category/add")
    public String addCategory(@ModelAttribute Category category) {
        service.saveCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("admin/category/delete/{id}")
    public String deleteCategory(@PathVariable int id){
        service.deleteCategory(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("admin/category/update/{id}")
    public String updateCategory(@PathVariable int id,Model model) {
        Category category = service.getCategoryById(id);
        model.addAttribute("category",category);
        return "add-category";
    }
}
