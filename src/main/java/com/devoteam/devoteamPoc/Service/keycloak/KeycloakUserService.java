package com.devoteam.devoteamPoc.Service.keycloak;

import com.devoteam.devoteamPoc.Dto.UserRegistrationRecord;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public interface KeycloakUserService {
    UserRegistrationRecord createUser( UserRegistrationRecord userRegistrationRecord);
    UserRepresentation getUserById(String userId, Jwt jwt);
    void deleteUserById(String userId);
     void emailVerification(String userId);
     UserResource getUserResource(String userId);
    void updatePassword(String userId);
    void forgotPassword(String username);
    List<UserRepresentation> getAllUsers();
    List<UserRepresentation> getAllUsersWithRolesAndAttributes();

}
