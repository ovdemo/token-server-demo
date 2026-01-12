package com.example.tokenserver.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityConfigTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RegisteredClientRepository registeredClientRepository;

    @Test
    void testRegisteredClientRepositoryBeanExists() {
        assertNotNull(registeredClientRepository);
    }

    @Test
    void testHealthEndpointIsAccessibleWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/actuator/health"))
                .andExpect(status().isOk());
    }

    @Test
    void testOAuthTokenEndpointRequiresAuthentication() throws Exception {
        mockMvc.perform(post("/oauth2/token")
                .param("grant_type", "client_credentials")
                .param("scope", "read"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void testOAuthTokenEndpointWithValidClientCredentials() throws Exception {
        mockMvc.perform(post("/oauth2/token")
                .with(httpBasic("client", "secret"))
                .param("grant_type", "client_credentials")
                .param("scope", "read"))
                .andExpect(status().isOk());
    }

    @Test
    void testOidcDiscoveryEndpoint() throws Exception {
        mockMvc.perform(get("/.well-known/openid-configuration"))
                .andExpect(status().isOk());
    }

    @Test
    void testJwksEndpoint() throws Exception {
        mockMvc.perform(get("/oauth2/jwks"))
                .andExpect(status().isOk());
    }

    @Test
    void testIntrospectionEndpointRequiresAuthentication() throws Exception {
        mockMvc.perform(post("/oauth2/introspect")
                .param("token", "dummy-token"))
                .andExpect(status().is3xxRedirection());
    }
}
