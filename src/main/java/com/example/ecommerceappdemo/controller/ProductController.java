package com.example.ecommerceappdemo.controller;

import com.example.ecommerceappdemo.dto.ProductDTO;
import com.example.ecommerceappdemo.entities.Product;
import com.example.ecommerceappdemo.service.CategoryService;
import com.example.ecommerceappdemo.service.ProductService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.validation.Valid;

@Controller
public class ProductController {

    public static String uploadDir = System.getProperty("user.dir") + "/uploads";

    private final ProductService service;
    private final CategoryService categoryService;

    public ProductController(ProductService service, CategoryService categoryService) {
        this.service = service;
        this.categoryService = categoryService;
    }

    @GetMapping("admin/products")
    public String getProducts(Model model,@Param("keyword") String keyword){
        model.addAttribute("products",service.findAllProduct(keyword));
        model.addAttribute("keyword",keyword);
        return "products";
    }

    @GetMapping("admin/product/add")
    public String addProduct(Model model){
        model.addAttribute("productDto",new ProductDTO());
        model.addAttribute("categories",categoryService.getAllCategory());
        return "add-product";
    }

    @PostMapping("admin/product/add")
    public String addProduct(@ModelAttribute(value = "productDto")@Valid ProductDTO productDto,
                             @RequestParam("productImage")MultipartFile file,
                             @RequestParam("imgName") String imgName)throws IOException{

    		
    		 Product product = new Product();
             product.setId(productDto.getId());
             product.setProductName(productDto.getName());
             product.setCategory(categoryService.getCategoryById(productDto.getCategoryId()));
             product.setPrice(productDto.getPrice());
             product.setWeight(productDto.getWeight());
             product.setDescription(productDto.getDescription());
        String imgaeUUid;
        if(!file.isEmpty()) {
            imgaeUUid = StringUtils.cleanPath(file.getOriginalFilename());
            var path = Paths.get(uploadDir,imgaeUUid);
            Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);

        }else {
            imgaeUUid = imgName;
        }

        product.setImageName(imgaeUUid);

        service.addProduct(product);


        return "redirect:/admin/products?keyword="+product.getProductName();
        
    	
    }

    @GetMapping("admin/product/delete/{id}")
    public String deleteProduct(@PathVariable int id){
        service.deleteProduct(id);
        return "redirect:/admin/products";
    }

    @GetMapping("admin/product/update/{id}")
    public String updateProduct(@PathVariable int id,Model model){
        Product product = service.findProductById(id);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getProductName());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setPrice(product.getPrice());
        productDTO.setWeight(product.getWeight());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageName(product.getImageName());

        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("productDto",productDTO);
        return "add-product";
    }


}
