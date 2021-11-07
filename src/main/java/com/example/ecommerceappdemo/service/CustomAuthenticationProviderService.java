package com.example.ecommerceappdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticationProviderService implements AuthenticationProvider {

    @Autowired
    private CustomUserDetailService userDetailService;

    @Autowired
    private PasswordEncoder encoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails user = userDetailService.loadUserByUsername(username);

        if(encoder.matches(password,user.getPassword())){
            return new UsernamePasswordAuthenticationToken(username,password,user.getAuthorities());
        }else {
            System.out.println("Wrong password");
            throw new BadCredentialsException("Wrong Password");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
