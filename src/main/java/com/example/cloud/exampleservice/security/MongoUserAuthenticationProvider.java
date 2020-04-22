package com.example.cloud.exampleservice.security;

import com.example.cloud.exampleservice.model.User;
import com.example.cloud.exampleservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MongoUserAuthenticationProvider implements AuthenticationManager {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //    Assert.notNull(authentication.getName(), "User mobile number is required");
        //    Assert.notNull(authentication.getCredentials().toString(), "OTP code is required");

        try {
            System.out.println("Before checking credentials");
            User user = userService.getUserByName(authentication.getName());
            // Check if password match
            if (authentication.getCredentials().toString().equals(user.getPassword())) {
                GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
                UserDetails authenticatedUser = new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), Arrays.asList(authority));

                Authentication checkedAuthentication = new UsernamePasswordAuthenticationToken(authenticatedUser,
                        authenticatedUser.getUsername(), authenticatedUser.getAuthorities());


                return checkedAuthentication;
            } else {
                System.out.println("Password does not match");
                throw new AuthenticationServiceException("Password does not match");
            }


        } catch (Exception exception) {
            System.out.println("Exception in mongo password check provider");
            throw new AuthenticationServiceException(exception.getMessage());
        }
    }
}
