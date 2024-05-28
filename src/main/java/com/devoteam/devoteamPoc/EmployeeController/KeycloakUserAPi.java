package com.devoteam.devoteamPoc.EmployeeController;


import com.devoteam.devoteamPoc.Config.JwtAuthConverter;
import com.devoteam.devoteamPoc.Dto.UserRegistrationRecord;
import com.devoteam.devoteamPoc.Service.keycloak.KeycloakUserService;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/users")
public class KeycloakUserAPi {


    @Autowired
    private final KeycloakUserService keycloakUserService;
    @Autowired
    private  final JwtAuthConverter jwtAuthConverter;

    public KeycloakUserAPi(KeycloakUserService keycloakUserService, JwtAuthConverter jwtAuthConverter) {
        this.keycloakUserService = keycloakUserService;
        this.jwtAuthConverter = jwtAuthConverter;
    }

    @PostMapping("/add-user")
    @PreAuthorize("hasRole('ADMIN')")
    public String createUser(@RequestBody UserRegistrationRecord userRegistrationRecord){

        return keycloakUserService.createUser(userRegistrationRecord);

    }
    @GetMapping
    public UserRepresentation getUser(Principal principal,@AuthenticationPrincipal Jwt jwt){

        return keycloakUserService.getUserById(principal.getName(),jwt);


    }
    @DeleteMapping("/{userId}")
    public void deleteUseById(@PathVariable String userId){
        keycloakUserService.deleteUserById(userId);

    }

    @PutMapping("/{userId}/send-verify-email")
    public void sendVerificationEmail(@PathVariable String userId) {
        keycloakUserService.emailVerification(userId);
    }
    @PutMapping("/updatePassword")
    public void updatePassword(Principal principal) {

        keycloakUserService.updatePassword(principal.getName());
    }

    @PutMapping("/{username}/forgot-Password")
    public void forgotPassword(@PathVariable String username) {
        keycloakUserService.forgotPassword(username);
    }


    //@CrossOrigin(origins = "http://localhost:3030")
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserRepresentation> getAllUsers() {
        return keycloakUserService.getAllUsers();
    }


    /*@GetMapping("/user-role")
    public void getUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        System.out.println("Roles de l'utilisateur connecté : ");
        authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .forEach(System.out::println);
    }

     */
    @GetMapping("/user-role")
    public String getUserRole(@AuthenticationPrincipal Jwt jwt) {
        // Récupérer le rôle de l'utilisateur à partir du token JWT
        String role = jwtAuthConverter.getRole(jwt);

        // Vérifier si un rôle a été récupéré
        if (role != null) {
            return role;
        } else {
            return "Impossible de récupérer le rôle de l'utilisateur";
        }
    }

}






