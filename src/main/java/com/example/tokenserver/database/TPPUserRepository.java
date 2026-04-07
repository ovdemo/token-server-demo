package com.example.tokenserver.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tokenserver.model.TPPUser;

import java.util.Optional;

@Repository
public interface TPPUserRepository extends JpaRepository<TPPUser, Long> {
    Optional<TPPUser> findByUsername(String username);
}
