package com.example.ecommerceappdemo.controller;

import com.example.ecommerceappdemo.entities.Product;
import com.example.ecommerceappdemo.service.ProductService;
import com.example.ecommerceappdemo.storagedata.ProductCartStorage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {

    private final ProductService productService;

    public CartController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("addToCart/{id}")
    public String addToCart(@PathVariable int id,Model model){
        ProductCartStorage.productList.add(productService.findProductById(id));
        model.addAttribute("cartCount",ProductCartStorage.productList.size());
        return "redirect:/shop";
    }

    @GetMapping("/cart")
    public String getCart(Model model){
        model.addAttribute("cartCount",ProductCartStorage.productList.size());
        model.addAttribute("totalPrice",ProductCartStorage.productList.stream()
        .mapToDouble(Product::getPrice).sum());
        model.addAttribute("products",ProductCartStorage.productList);
        return "cart";
    }

    @GetMapping("/cart/remove/item/{index}")
    public String cartRemoveItem(@PathVariable int index){
        ProductCartStorage.productList.remove((index-1));
        return "redirect:/cart";
    }

    @GetMapping("/cart/checkout")
    public String cartCheckout(Model model){
        model.addAttribute("cartCount",ProductCartStorage.productList.size());
        model.addAttribute("totalPrice",ProductCartStorage.productList.stream()
                .mapToDouble(Product::getPrice).sum());
        model.addAttribute("products",ProductCartStorage.productList);
        return "checkout";
    }

    @GetMapping("payment/order")
    public String successOrder(){
        ProductCartStorage.productList.clear();
        return "order-success";
    }

}
