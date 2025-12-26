package com.example.demo.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    private final Map<String, UserDetails> users = new HashMap<>();
    
    public CustomUserDetailsService() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        users.put("admin@example.com", User.builder()
            .username("admin@example.com")
            .password(encoder.encode("admin123"))
            .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")))
            .build());
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails user = users.get(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + email);
        }
        return user;
    }
}