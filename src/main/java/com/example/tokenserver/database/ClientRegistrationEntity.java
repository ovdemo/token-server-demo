package com.example.tokenserver.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CLIENT_REGISTRATION")
public class ClientRegistrationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String clientId;

    private Integer clientIdIssuedAt;

    @Column(nullable = false)
    private String clientSecret;

    // OBClientRegistration1 JWT claims
    private String iss;
    private Integer iat;
    private Integer exp;
    private String aud;
    private String jti;

    // OBRegistrationProperties1 — list fields stored as comma-separated text
    @Column(columnDefinition = "TEXT")
    private String redirectUris;

    private String tokenEndpointAuthMethod;

    @Column(columnDefinition = "TEXT")
    private String grantTypes;

    @Column(columnDefinition = "TEXT")
    private String responseTypes;

    private String softwareId;

    @Column(length = 256)
    private String scope;

    @Column(columnDefinition = "TEXT")
    private String softwareStatement;

    private String applicationType;
    private String idTokenSignedResponseAlg;
    private String requestObjectSigningAlg;
    private String tokenEndpointAuthSigningAlg;

    @Column(length = 512)
    private String tlsClientAuthSubjectDn;

    private String backchannelTokenDeliveryMode;

    @Column(length = 256)
    private String backchannelClientNotificationEndpoint;

    private String backchannelAuthenticationRequestSigningAlg;
    private Boolean backchannelUserCodeParameterSupported;

    public Long getId() { return id; }

    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public Integer getClientIdIssuedAt() { return clientIdIssuedAt; }
    public void setClientIdIssuedAt(Integer clientIdIssuedAt) { this.clientIdIssuedAt = clientIdIssuedAt; }

    public String getClientSecret() { return clientSecret; }
    public void setClientSecret(String clientSecret) { this.clientSecret = clientSecret; }

    public String getIss() { return iss; }
    public void setIss(String iss) { this.iss = iss; }

    public Integer getIat() { return iat; }
    public void setIat(Integer iat) { this.iat = iat; }

    public Integer getExp() { return exp; }
    public void setExp(Integer exp) { this.exp = exp; }

    public String getAud() { return aud; }
    public void setAud(String aud) { this.aud = aud; }

    public String getJti() { return jti; }
    public void setJti(String jti) { this.jti = jti; }

    public String getRedirectUris() { return redirectUris; }
    public void setRedirectUris(String redirectUris) { this.redirectUris = redirectUris; }

    public String getTokenEndpointAuthMethod() { return tokenEndpointAuthMethod; }
    public void setTokenEndpointAuthMethod(String tokenEndpointAuthMethod) { this.tokenEndpointAuthMethod = tokenEndpointAuthMethod; }

    public String getGrantTypes() { return grantTypes; }
    public void setGrantTypes(String grantTypes) { this.grantTypes = grantTypes; }

    public String getResponseTypes() { return responseTypes; }
    public void setResponseTypes(String responseTypes) { this.responseTypes = responseTypes; }

    public String getSoftwareId() { return softwareId; }
    public void setSoftwareId(String softwareId) { this.softwareId = softwareId; }

    public String getScope() { return scope; }
    public void setScope(String scope) { this.scope = scope; }

    public String getSoftwareStatement() { return softwareStatement; }
    public void setSoftwareStatement(String softwareStatement) { this.softwareStatement = softwareStatement; }

    public String getApplicationType() { return applicationType; }
    public void setApplicationType(String applicationType) { this.applicationType = applicationType; }

    public String getIdTokenSignedResponseAlg() { return idTokenSignedResponseAlg; }
    public void setIdTokenSignedResponseAlg(String idTokenSignedResponseAlg) { this.idTokenSignedResponseAlg = idTokenSignedResponseAlg; }

    public String getRequestObjectSigningAlg() { return requestObjectSigningAlg; }
    public void setRequestObjectSigningAlg(String requestObjectSigningAlg) { this.requestObjectSigningAlg = requestObjectSigningAlg; }

    public String getTokenEndpointAuthSigningAlg() { return tokenEndpointAuthSigningAlg; }
    public void setTokenEndpointAuthSigningAlg(String tokenEndpointAuthSigningAlg) { this.tokenEndpointAuthSigningAlg = tokenEndpointAuthSigningAlg; }

    public String getTlsClientAuthSubjectDn() { return tlsClientAuthSubjectDn; }
    public void setTlsClientAuthSubjectDn(String tlsClientAuthSubjectDn) { this.tlsClientAuthSubjectDn = tlsClientAuthSubjectDn; }

    public String getBackchannelTokenDeliveryMode() { return backchannelTokenDeliveryMode; }
    public void setBackchannelTokenDeliveryMode(String backchannelTokenDeliveryMode) { this.backchannelTokenDeliveryMode = backchannelTokenDeliveryMode; }

    public String getBackchannelClientNotificationEndpoint() { return backchannelClientNotificationEndpoint; }
    public void setBackchannelClientNotificationEndpoint(String backchannelClientNotificationEndpoint) { this.backchannelClientNotificationEndpoint = backchannelClientNotificationEndpoint; }

    public String getBackchannelAuthenticationRequestSigningAlg() { return backchannelAuthenticationRequestSigningAlg; }
    public void setBackchannelAuthenticationRequestSigningAlg(String backchannelAuthenticationRequestSigningAlg) { this.backchannelAuthenticationRequestSigningAlg = backchannelAuthenticationRequestSigningAlg; }

    public Boolean getBackchannelUserCodeParameterSupported() { return backchannelUserCodeParameterSupported; }
    public void setBackchannelUserCodeParameterSupported(Boolean backchannelUserCodeParameterSupported) { this.backchannelUserCodeParameterSupported = backchannelUserCodeParameterSupported; }
}
