package com.example.tokenserver.service;

import com.example.tokenserver.database.ClientRegistrationEntity;
import com.example.tokenserver.database.ClientRegistrationRepository;
import com.example.tokenserver.model.ClientRegistrationRequest;
import com.example.tokenserver.model.ClientRegistrationResponse;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientRegistrationService {

    private final ClientRegistrationRepository repository;
    private final PasswordEncoder passwordEncoder;

    public ClientRegistrationService(ClientRegistrationRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public ClientRegistrationResponse register(ClientRegistrationRequest request) {
        String rawSecret = UUID.randomUUID().toString();
        ClientRegistrationEntity entity = new ClientRegistrationEntity();
        entity.setClientId(UUID.randomUUID().toString());
        entity.setClientIdIssuedAt((int) Instant.now().getEpochSecond());
        entity.setClientSecret(passwordEncoder.encode(rawSecret));
        mapRequestToEntity(request, entity);
        ClientRegistrationEntity saved = repository.save(entity);
        ClientRegistrationResponse response = mapEntityToResponse(saved);
        response.setClientSecret(rawSecret);
        return response;
    }

    public Optional<ClientRegistrationResponse> getByClientId(String clientId) {
        return repository.findByClientId(clientId).map(this::mapEntityToResponse);
    }

    public Optional<ClientRegistrationResponse> update(String clientId, ClientRegistrationRequest request) {
        return repository.findByClientId(clientId).map(entity -> {
            mapRequestToEntity(request, entity);
            return mapEntityToResponse(repository.save(entity));
        });
    }

    @Transactional
    public boolean delete(String clientId) {
        if (!repository.existsByClientId(clientId)) {
            return false;
        }
        repository.deleteByClientId(clientId);
        return true;
    }

    private void mapRequestToEntity(ClientRegistrationRequest request, ClientRegistrationEntity entity) {
        entity.setIss(request.getIss());
        entity.setIat(request.getIat());
        entity.setExp(request.getExp());
        entity.setAud(request.getAud());
        entity.setJti(request.getJti());
        entity.setRedirectUris(joinList(request.getRedirectUris()));
        entity.setTokenEndpointAuthMethod(request.getTokenEndpointAuthMethod());
        entity.setGrantTypes(joinList(request.getGrantTypes()));
        entity.setResponseTypes(joinList(request.getResponseTypes()));
        entity.setSoftwareId(request.getSoftwareId());
        entity.setScope(request.getScope());
        entity.setSoftwareStatement(request.getSoftwareStatement());
        entity.setApplicationType(request.getApplicationType());
        entity.setIdTokenSignedResponseAlg(request.getIdTokenSignedResponseAlg());
        entity.setRequestObjectSigningAlg(request.getRequestObjectSigningAlg());
        entity.setTokenEndpointAuthSigningAlg(request.getTokenEndpointAuthSigningAlg());
        entity.setTlsClientAuthSubjectDn(request.getTlsClientAuthSubjectDn());
        entity.setBackchannelTokenDeliveryMode(request.getBackchannelTokenDeliveryMode());
        entity.setBackchannelClientNotificationEndpoint(request.getBackchannelClientNotificationEndpoint());
        entity.setBackchannelAuthenticationRequestSigningAlg(request.getBackchannelAuthenticationRequestSigningAlg());
        entity.setBackchannelUserCodeParameterSupported(request.getBackchannelUserCodeParameterSupported());
    }

    private ClientRegistrationResponse mapEntityToResponse(ClientRegistrationEntity entity) {
        ClientRegistrationResponse response = new ClientRegistrationResponse();
        response.setClientId(entity.getClientId());
        response.setClientIdIssuedAt(entity.getClientIdIssuedAt());
        response.setClientSecretExpiresAt(0);
        response.setIss(entity.getIss());
        response.setIat(entity.getIat());
        response.setExp(entity.getExp());
        response.setAud(entity.getAud());
        response.setJti(entity.getJti());
        response.setRedirectUris(splitList(entity.getRedirectUris()));
        response.setTokenEndpointAuthMethod(entity.getTokenEndpointAuthMethod());
        response.setGrantTypes(splitList(entity.getGrantTypes()));
        response.setResponseTypes(splitList(entity.getResponseTypes()));
        response.setSoftwareId(entity.getSoftwareId());
        response.setScope(entity.getScope());
        response.setSoftwareStatement(entity.getSoftwareStatement());
        response.setApplicationType(entity.getApplicationType());
        response.setIdTokenSignedResponseAlg(entity.getIdTokenSignedResponseAlg());
        response.setRequestObjectSigningAlg(entity.getRequestObjectSigningAlg());
        response.setTokenEndpointAuthSigningAlg(entity.getTokenEndpointAuthSigningAlg());
        response.setTlsClientAuthSubjectDn(entity.getTlsClientAuthSubjectDn());
        response.setBackchannelTokenDeliveryMode(entity.getBackchannelTokenDeliveryMode());
        response.setBackchannelClientNotificationEndpoint(entity.getBackchannelClientNotificationEndpoint());
        response.setBackchannelAuthenticationRequestSigningAlg(entity.getBackchannelAuthenticationRequestSigningAlg());
        response.setBackchannelUserCodeParameterSupported(entity.getBackchannelUserCodeParameterSupported());
        return response;
    }

    private String joinList(List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return String.join(",", list);
    }

    private List<String> splitList(String value) {
        if (value == null || value.isBlank()) {
            return Collections.emptyList();
        }
        return Arrays.asList(value.split(","));
    }
}
