package com.example.ecommerceappdemo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Configuration
public class LoginHandlerConfiguration implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        String redirectUrl = null;
        Collection<? extends  GrantedAuthority> authorities = authentication.getAuthorities();

        for(GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")){
                redirectUrl = "/admin";
                break;
            }else {
                redirectUrl = "/shop";
                break;
            }
        }

        redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,redirectUrl);

    }
}
