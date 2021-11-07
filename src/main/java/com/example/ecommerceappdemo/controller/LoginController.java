package com.example.ecommerceappdemo.controller;

import com.example.ecommerceappdemo.entities.User;
import com.example.ecommerceappdemo.repository.UserRepository;
import com.example.ecommerceappdemo.service.CustomUserDetailService;
import com.example.ecommerceappdemo.storagedata.ProductCartStorage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class LoginController {

    private final UserRepository userRepository;
    private final CustomUserDetailService userService;

    public LoginController(UserRepository userRepository, CustomUserDetailService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        ProductCartStorage.productList.clear();
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }


    @GetMapping("register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("register")
    public String registerUserAccount(@ModelAttribute @Valid User user, BindingResult bindingResult) {

//      Check email exist
        isEmailExit(user,bindingResult);

//      Check password and confirmPassword is match
        isMatchPassword(user,bindingResult);

        if (bindingResult.hasErrors()){
            return "register";
        }else {
            userService.userRegister(user);
            return "redirect:/login";
        }
    }

    private void isEmailExit(User user, BindingResult bindingResult) {
        if (userService.isUserExit(user.getEmail())) {
            bindingResult.addError(new FieldError("user","email","This email already in use"));
        }
    }

    private void isMatchPassword(User user,BindingResult bindingResult) {
        if(user.getPassword() != null & user.getConfirmPassword() != null){
            if(!user.getPassword().equals(user.getConfirmPassword())){
                bindingResult.addError(new FieldError("user","confirmPassword",
                        "Password must be match"));
            }
        }
    }

}
