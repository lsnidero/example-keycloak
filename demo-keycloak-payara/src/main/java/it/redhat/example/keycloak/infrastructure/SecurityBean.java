package it.redhat.example.keycloak.infrastructure;

import javax.enterprise.context.ApplicationScoped;

import fish.payara.security.annotations.LogoutDefinition;
import fish.payara.security.annotations.OpenIdAuthenticationDefinition;

@OpenIdAuthenticationDefinition(
    providerURI = "http://192.168.1.192:8082/auth/realms/latina",
    clientId = "payara",
    clientSecret = "432G1rt8YY52wNAa324Nyvamz00lo3Id",
    redirectURI = "${baseURL}/Callback",
    logout = @LogoutDefinition(notifyProvider = true, redirectURI = "${baseURL}/") )
@ApplicationScoped
public class SecurityBean {
    
}
