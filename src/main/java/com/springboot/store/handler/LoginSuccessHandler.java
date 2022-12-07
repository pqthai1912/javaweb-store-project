package com.springboot.store.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.springboot.store.models.User;
import com.springboot.store.other.UserRole;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
 

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
 
        User userDetails = (User) authentication.getPrincipal();
         
//      String redirectURL = request.getContextPath();
        String redirectURL = "/";
        for (UserRole userRole : userDetails.getUserRoles()) {
        	if (userRole.getRole().getName().equals("ROLE_ADMIN")) {
                redirectURL = "/dashboard";
            } 
		}

        response.sendRedirect(redirectURL);
         
    }
 
}