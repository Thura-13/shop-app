package com.example.ecommerceappdemo.model;

import com.example.ecommerceappdemo.entities.Roles;
import com.example.ecommerceappdemo.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetail  implements UserDetails {

    private User user;

    public CustomUserDetail(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

//        Default Role
        List<Roles> roles = new ArrayList<>();
        roles.add(new Roles("ROLE_CUSTOMER"));
        user.setRoles(roles);

        return user.getRoles().stream().map(r->new SimpleGrantedAuthority(r.getRoleName())
        ).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public String getPassword(){
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
