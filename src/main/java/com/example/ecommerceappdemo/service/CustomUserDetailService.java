package com.example.ecommerceappdemo.service;

import com.example.ecommerceappdemo.model.CustomUserDetail;
import com.example.ecommerceappdemo.entities.User;
import com.example.ecommerceappdemo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService  implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("Problem during authentication"));
        return new CustomUserDetail(user);
    }

    public boolean isUserExit(String email){
        return userRepository.findUserByEmail(email).isPresent();
    }

    public User userRegister(User u) {
        String password = encoder.encode(u.getPassword());
        String confirmPassword = encoder.encode(u.getConfirmPassword());
        u.setPassword(password);
        u.setConfirmPassword(confirmPassword);
        User user = new User();
        user.setFirstName(u.getFirstName());
        user.setLastName(u.getLastName());
        user.setEmail(u.getEmail());
        user.setPassword(u.getPassword());
        user.setConfirmPassword(u.getConfirmPassword());
        return userRepository.save(user);
    }
}
