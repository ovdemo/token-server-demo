package com.example.tokenserver.service;

import com.example.tokenserver.database.TPPUserRepository;
import com.example.tokenserver.model.TPPUser;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TPPUserDetailsService implements UserDetailsService {

    private final TPPUserRepository tppUserRepository;
    private final PasswordEncoder passwordEncoder;

    public TPPUserDetailsService(TPPUserRepository tppUserRepository, PasswordEncoder passwordEncoder) {
        this.tppUserRepository = tppUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return tppUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public TPPUser addUser(String username, String password) {
        TPPUser user = new TPPUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        return tppUserRepository.save(user);
    }


}
