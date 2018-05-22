package com.example.demo.security.service;

import com.example.demo.security.model.CustomUserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("User")){
            CustomUserDetails userDetails = new CustomUserDetails();
            userDetails.setId(1);
            userDetails.setUsername("User");
            userDetails.setPassword("123321");
            userDetails.setAuthorities(Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
            return userDetails;
        }else{
            throw new UsernameNotFoundException("User not found!");
        }
    }
}
