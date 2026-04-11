package com.example.tokenserver.controller;

import com.example.tokenserver.model.ClientRegistrationRequest;
import com.example.tokenserver.model.ClientRegistrationResponse;
import com.example.tokenserver.model.RegistrationError;
import com.example.tokenserver.service.ClientRegistrationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implements the Open Banking Dynamic Client Registration API (v3.4).
 *
 * POST   /register              — Register a client (secured by mTLS in production)
 * GET    /register/{ClientId}   — Retrieve a client (requires OAuth2 bearer token)
 * PUT    /register/{ClientId}   — Update a client   (requires OAuth2 bearer token)
 * DELETE /register/{ClientId}   — Delete a client   (requires OAuth2 bearer token)
 *
 * NOTE: The spec requires the POST/PUT request body to be a signed JWT (SSA).
 * For demo purposes, the body is accepted as a plain JSON object.
 */
@RestController
@RequestMapping("/register")
public class ClientRegistrationController {

    private final ClientRegistrationService service;

    public ClientRegistrationController(ClientRegistrationService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody ClientRegistrationRequest request) {
        if (request.getRedirectUris() == null || request.getRedirectUris().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new RegistrationError("invalid_client_metadata", "redirect_uris is required"));
        }
        if (request.getTokenEndpointAuthMethod() == null) {
            return ResponseEntity.badRequest()
                    .body(new RegistrationError("invalid_client_metadata", "token_endpoint_auth_method is required"));
        }
        if (request.getGrantTypes() == null || request.getGrantTypes().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new RegistrationError("invalid_client_metadata", "grant_types is required"));
        }
        if (request.getScope() == null) {
            return ResponseEntity.badRequest()
                    .body(new RegistrationError("invalid_client_metadata", "scope is required"));
        }
        if (request.getSoftwareStatement() == null) {
            return ResponseEntity.badRequest()
                    .body(new RegistrationError("invalid_software_statement", "software_statement is required"));
        }
        if (request.getApplicationType() == null) {
            return ResponseEntity.badRequest()
                    .body(new RegistrationError("invalid_client_metadata", "application_type is required"));
        }
        if (request.getIdTokenSignedResponseAlg() == null) {
            return ResponseEntity.badRequest()
                    .body(new RegistrationError("invalid_client_metadata", "id_token_signed_response_alg is required"));
        }
        if (request.getRequestObjectSigningAlg() == null) {
            return ResponseEntity.badRequest()
                    .body(new RegistrationError("invalid_client_metadata", "request_object_signing_alg is required"));
        }

        ClientRegistrationResponse response = service.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRegistration(@PathVariable String clientId) {
        return service.getByClientId(clientId)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new RegistrationError("invalid_client_metadata", "Unknown client: " + clientId)));
    }

    @PutMapping(value = "/{clientId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateRegistration(@PathVariable String clientId,
                                                @RequestBody ClientRegistrationRequest request) {
        return service.update(clientId, request)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new RegistrationError("invalid_client_metadata", "Unknown client: " + clientId)));
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable String clientId) {
        if (!service.delete(clientId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.noContent().build();
    }
}
