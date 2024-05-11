package com.devoteam.devoteamPoc.EmployeeController;


import com.devoteam.devoteamPoc.Service.keycloak.KeycloakUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicAPI {
    @Autowired
    private final KeycloakUserService keycloakUserService;

    public PublicAPI(KeycloakUserService keycloakUserService) {
        this.keycloakUserService = keycloakUserService;
    }

    @PutMapping("/{username}/forgot-Password")
    public void forgotPassword(@PathVariable String username) {
        keycloakUserService.forgotPassword(username);
    }
}
