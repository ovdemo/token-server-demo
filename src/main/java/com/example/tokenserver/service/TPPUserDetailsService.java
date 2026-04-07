package com.example.tokenserver.service;

import com.example.tokenserver.database.TPPUserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TPPUserDetailsService implements UserDetailsService {

    private final TPPUserRepository tppUserRepository;

    public TPPUserDetailsService(TPPUserRepository tppUserRepository) {
        this.tppUserRepository = tppUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return tppUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
