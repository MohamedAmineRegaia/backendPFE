package com.devoteam.devoteamPoc.Service.impl;


import com.devoteam.devoteamPoc.Config.JwtAuthConverter;
import com.devoteam.devoteamPoc.Dto.UserRegistrationRecord;
import com.devoteam.devoteamPoc.Service.keycloak.KeycloakUserService;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class KeycloakUserServiceImpl implements KeycloakUserService {

    @Value("testrealm")
    private String realm;
    private Keycloak keycloak;
    private static final String UPDATE_PASSWORD = "UPDATE_PASSWORD";
    private final DataSource dataSource;
    @Autowired
    private JwtAuthConverter jwtAuthConverter;

    public KeycloakUserServiceImpl(Keycloak keycloak, DataSource dataSource) {
        this.keycloak = keycloak;
        this.dataSource = dataSource;
    }

    @Override
    public String createUser(UserRegistrationRecord userRegistrationRecord) {


        UserRepresentation user=new UserRepresentation();

        user.setEnabled(true);
        user.setUsername(userRegistrationRecord.username());
        user.setEmail(userRegistrationRecord.email());
        user.setFirstName(userRegistrationRecord.firstName());
        user.setLastName(userRegistrationRecord.lastName());
        user.setEmailVerified(false);

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(userRegistrationRecord.password());
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(credentialRepresentation.PASSWORD);

        List<CredentialRepresentation> list = new ArrayList<>();
        list.add(credentialRepresentation);
        user.setCredentials(list);
        Map<String, List<String>> attributes = new HashMap<>();
        attributes.put("disponibilite", Collections.singletonList(userRegistrationRecord.disponibilite()));
        attributes.put("autreAttribut", Collections.singletonList(userRegistrationRecord.autreAttribut()));

        // Attribution des attributs à l'utilisateur
        user.setAttributes(attributes);



        UsersResource usersResource = getUsersResource();

        Response response = usersResource.create(user);

        if(Objects.equals(201,response.getStatus())){
            List<UserRepresentation> representationList = usersResource.searchByUsername(userRegistrationRecord.username(), true);
            if(!CollectionUtils.isEmpty(representationList)){
                UserRepresentation userRepresentation1 = representationList.stream().filter(userRepresentation -> Objects.equals(false, userRepresentation.isEmailVerified())).findFirst().orElse(null);
                assert userRepresentation1 != null;
                emailVerification(userRepresentation1.getId());
                forgotPassword(userRegistrationRecord.username());

            }



            return "user add succesfully ";
        }

        return "error";
    }

    private UsersResource getUsersResource() {
        RealmResource realm1 = keycloak.realm(realm);
        return realm1.users();
    }

    @Override
    public UserRepresentation getUserById(String userId, Jwt jwt) {
        UserResource userResource = getUsersResource().get(userId);
        UserRepresentation userRepresentation = userResource.toRepresentation();

        // Vérifier si l'e-mail est vérifié
        boolean emailVerified = userRepresentation.isEmailVerified();

        // Si l'e-mail est vérifié, ajouter les rôles autorisés de l'utilisateur à partir du token JWT
        if (emailVerified) {
            List<String> roles = getUserRoles(jwt);
            // Créer une liste avec la chaîne de rôle
            List<String> realmRoles = new ArrayList<>();
            realmRoles.add(String.join(", ", roles));
            // Définir les rôles dans userRepresentation
            userRepresentation.setRealmRoles(realmRoles);
        } else {
            userRepresentation.getAttributes().put("verification_status", List.of("Please verify your email."));
        }

        return userRepresentation;
    }



    private List<String> getUserRoles(Jwt jwt) {
        // Récupérer les rôles à partir du token JWT
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        if (realmAccess != null && realmAccess.containsKey("roles")) {
            Collection<String> allRoles = (Collection<String>) realmAccess.get("roles");
            // Filtrer les rôles autorisés
            return allRoles.stream()
                    .filter(role -> role.equals("ADMIN") || role.equals("MANAGER") || role.equals("COMMERCIAL") || role.equals("STAFF"))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }


    @Override
    public void deleteUserById(String userId) {
        getUsersResource().delete(userId);

    }
    @Override
    public void emailVerification(String userId){

        UsersResource usersResource = getUsersResource();
        usersResource.get(userId).sendVerifyEmail();
    }
    @Override
    public void forgotPassword(String username) {
        UsersResource usersResource = getUsersResource();
        List<UserRepresentation> representationList = usersResource.searchByUsername(username, true);
        UserRepresentation userRepresentation = representationList.stream().findFirst().orElse(null);
        if(userRepresentation!=null){
            System.out.print("i'm here forgetpwd");

            UserResource userResource = usersResource.get(userRepresentation.getId());
            List<String> actions = new ArrayList<>();

            actions.add(UPDATE_PASSWORD);
            userResource.executeActionsEmail(actions);
            return;
        }
        throw new RuntimeException("username not found");


    }

    public UserResource getUserResource(String userId){
        UsersResource usersResource = getUsersResource();
        return usersResource.get(userId);
    }

    @Override
    public void updatePassword(String userId) {

        UserResource userResource = getUserResource(userId);

        List<String> actions = new ArrayList<>();
        actions.add("UPDATE_PASSWORD");
        userResource.executeActionsEmail(actions);


    }
    private List<String> getUserRealmRoles(String userId) {
        UserResource userResource = getUsersResource().get(userId);
        List<RoleRepresentation> roles = userResource.roles().realmLevel().listAll();
        return roles.stream()
                .map(RoleRepresentation::getName)
                .filter(roleName -> roleName.equals("ADMIN") || roleName.equals("MANAGER") || roleName.equals("STAFF") || roleName.equals("COMMERCIAL"))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserRepresentation> getAllUsers() {
        UsersResource usersResource = getUsersResource();
        List<UserRepresentation> allUsers = new ArrayList<>();

        List<UserRepresentation> userList = usersResource.list();
        for (UserRepresentation user : userList) {
            String userId = user.getId();
            List<String> realmRoles = getUserRealmRoles(userId);

           // System.out.print(realmRoles);
            user.setRealmRoles(realmRoles);
            allUsers.add(user);
        }

        return allUsers;
    }




    //SELECT
//    ue.ID AS ID_user,
//    ue.EMAIL,
//    ue.EMAIL_VERIFIED,
//    ue.FIRST_NAME,
//    ue.LAST_NAME,
//    ue.USERNAME,
//    GROUP_CONCAT(kr.NAME SEPARATOR ', ') AS roles,
//    GROUP_CONCAT(CONCAT(ua.NAME, ': ', ua.VALUE) SEPARATOR ', ') AS user_attributes
//FROM
//    USER_ENTITY ue
//LEFT JOIN
//    USER_ATTRIBUTE ua ON ue.ID = ua.USER_ID
//LEFT JOIN
//    USER_ROLE_MAPPING rm ON ue.ID = rm.USER_ID
//LEFT JOIN
//    KEYCLOAK_ROLE kr ON rm.ROLE_ID = kr.ID
//WHERE
//    ue.ID <> '73afb3d6-06e0-447e-aa57-d1ee19fb6530'
//GROUP BY
//    ue.ID;
    @Override
public List<UserRepresentation> getAllUsersWithRolesAndAttributes() {
    List<UserRepresentation> usersWithRolesAndAttributes = new ArrayList<>();

    try (Connection connection = dataSource.getConnection()) {
        Statement statement = connection.createStatement();
        String query = "SELECT " +
                "ue.ID AS ID_user, " +
                "ue.EMAIL, " +
                "ue.EMAIL_VERIFIED, " +
                "ue.FIRST_NAME, " +
                "ue.LAST_NAME, " +
                "ue.USERNAME, " +
                "GROUP_CONCAT(kr.NAME SEPARATOR ', ') AS roles, " +
                "GROUP_CONCAT(CONCAT(ua.NAME, ': ', ua.VALUE) SEPARATOR ', ') AS user_attributes " +
                "FROM " +
                "USER_ENTITY ue " +
                "LEFT JOIN " +
                "USER_ATTRIBUTE ua ON ue.ID = ua.USER_ID " +
                "LEFT JOIN " +
                "USER_ROLE_MAPPING rm ON ue.ID = rm.USER_ID " +
                "LEFT JOIN " +
                "KEYCLOAK_ROLE kr ON rm.ROLE_ID = kr.ID " +
                "WHERE " +
                "ue.ID <> '73afb3d6-06e0-447e-aa57-d1ee19fb6530' " +
                "GROUP BY " +
                "ue.ID";
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            UserRepresentation user = new UserRepresentation();
            user.setId(resultSet.getString("ID_user"));
            user.setEmail(resultSet.getString("EMAIL"));
            user.setEmailVerified(resultSet.getBoolean("EMAIL_VERIFIED"));
            user.setFirstName(resultSet.getString("FIRST_NAME"));
            user.setLastName(resultSet.getString("LAST_NAME"));
            user.setUsername(resultSet.getString("USERNAME"));

            String roles = resultSet.getString("roles");
            if (roles != null && !roles.isEmpty()) {
                // Split the roles string and set realm roles
                String[] rolesArray = roles.split(", ");
                user.setRealmRoles(Arrays.asList(rolesArray));
            }

            // Extract user attributes
            String userAttributesString = resultSet.getString("user_attributes");
            if (userAttributesString != null && !userAttributesString.isEmpty()) {
                // Split the user attributes string
                String[] userAttributesArray = userAttributesString.split(", ");
                for (String attribute : userAttributesArray) {
                    // Split attribute name and value
                    String[] attributeParts = attribute.split(": ");
                    if (attributeParts.length == 2) {
                        // Add attribute to user representation
                        user.singleAttribute(attributeParts[0], attributeParts[1]);
                    }
                }
            }

            usersWithRolesAndAttributes.add(user);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return usersWithRolesAndAttributes;
}

}
