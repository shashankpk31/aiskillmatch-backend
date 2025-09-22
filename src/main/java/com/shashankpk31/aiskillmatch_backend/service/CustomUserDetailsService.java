package com.shashankpk31.aiskillmatch_backend.service;

import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shashankpk31.aiskillmatch_backend.entity.UserAuth;
import com.shashankpk31.aiskillmatch_backend.repository.UserAuthRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserAuthRepository UserAuthRepository;
    public CustomUserDetailsService(UserAuthRepository UserAuthRepository) {
        this.UserAuthRepository = UserAuthRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuth user = UserAuthRepository.findByUsername(username)
           .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(Collections.singleton(authority))
                .accountLocked(false).disabled(!Boolean.TRUE.equals(user.getIsActive()))
                .build();
    }
}
