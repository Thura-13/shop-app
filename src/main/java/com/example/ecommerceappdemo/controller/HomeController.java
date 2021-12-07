package com.example.ecommerceappdemo.controller;

import com.example.ecommerceappdemo.service.CategoryService;
import com.example.ecommerceappdemo.service.ProductService;
import com.example.ecommerceappdemo.storagedata.ProductCartStorage;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public HomeController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping({"/","home"})
    public String home(Model model){
        model.addAttribute("cartCount", ProductCartStorage.productList.size());
        return "home";
    }

    @GetMapping("shop")
    public String shop(Model model,@Param("keyword") String keyword){
        model.addAttribute("products",productService.findAllProduct(keyword));
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("cartCount", ProductCartStorage.productList.size());
        return "shop";
    }

    @GetMapping("shop/category/{id}")
    public String shopByCategory(Model model , @PathVariable int id){
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("products",productService.getAllProductByCategoryId(id));
        model.addAttribute("cartCount", ProductCartStorage.productList.size());
        return "shop";
    }

    @GetMapping("shop/view/product/{id}")
    public String viewProduct(Model model,@PathVariable int id){
        model.addAttribute("products",productService.findProductById(id));
        model.addAttribute("cartCount", ProductCartStorage.productList.size());
        return "view-product";
    }
}
