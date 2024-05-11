package com.devoteam.devoteamPoc.Service.impl;

import com.devoteam.devoteamPoc.Service.keycloak.KeycloakUserService;
import com.devoteam.devoteamPoc.Service.keycloak.RoleService;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class RoleServiceImpl implements RoleService {

    @Value("testrealm")
    private String realm;
    private final Keycloak keycloak;
    private final KeycloakUserService keycloakUserService;

    public RoleServiceImpl(Keycloak keycloak, KeycloakUserService keycloakUserService) {
        this.keycloak = keycloak;
        this.keycloakUserService = keycloakUserService;
    }


    @Override
    public void assignRole(String userId, String roleName) {
        UserResource userResource = keycloakUserService.getUserResource(userId);
        RolesResource rolesResource = getRolesResource();
        RoleRepresentation representation = rolesResource.get(roleName).toRepresentation();
        userResource.roles().realmLevel().add(Collections.singletonList(representation));



    }

    private RolesResource getRolesResource(){
        return  keycloak.realm(realm).roles();
    }

}
