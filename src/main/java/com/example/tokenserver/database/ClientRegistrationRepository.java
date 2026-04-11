package com.example.tokenserver.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRegistrationRepository extends JpaRepository<ClientRegistrationEntity, Long> {

    Optional<ClientRegistrationEntity> findByClientId(String clientId);

    boolean existsByClientId(String clientId);

    void deleteByClientId(String clientId);
}
