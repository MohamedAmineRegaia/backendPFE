package com.devoteam.devoteamPoc.Config;


import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Value("admin-cli")
    private String adminClientId;
    @Value("Q0ihAzWjdQAmFkyREFIhXHSr3hW841jP")
    private String adminClientSecret;
    @Value("${keycloak.domain}")
    private String authServerUrl;

    @Value("testrealm")
    private String realm;
    @Bean
    public Keycloak keycloak(){
        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:8080")
                .realm("testrealm")
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId("admin-cli")
                .clientSecret("Q0ihAzWjdQAmFkyREFIhXHSr3hW841jP")
                .build();
    }
}
