package com.devoteam.devoteamPoc.Config;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    public static final String ADMIN = "admin";
    public static final String GENERAL = "general";
    private final JwtAuthConverter jwtAuthConverter;

    public WebSecurityConfig(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {


        httpSecurity

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/test/anonymous", "/test/anonymous/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/public/{username}/forgot-Password").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/all").hasAnyRole("ADMIN","MANAGER")
                        .requestMatchers(HttpMethod.GET, "/users/add-user").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/roles").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/Staff-Projet/affecter").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/users/update-password").permitAll()
                        .requestMatchers(HttpMethod.POST, "/staff-details").permitAll()
                        .requestMatchers(HttpMethod.POST, "/staff-details/details/manager").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/users/{userId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "Staff-Certification/update/{id}").hasRole("STAFF")
                        .requestMatchers(HttpMethod.GET, "propale/historique").hasAnyRole("COMMERCIAL","MANAGER")




                        .requestMatchers(HttpMethod.GET, "/test/admin", "/test/admin/**").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.GET, "/test/user").hasAnyRole(GENERAL)
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthConverter)
                        )
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.cors(c -> c.configurationSource(corsConfigurationSource()));
        return httpSecurity.build();

    }

    @Bean public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3030")); // URL de votre front-end React
     configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
     configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
     configuration.setAllowCredentials(true);
     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
     source.registerCorsConfiguration("/**", configuration);
     return source; }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        return (web) -> {
            web.ignoring().requestMatchers(
                    HttpMethod.POST,
                    "/public/**",
                    "/users"

            );
            web.ignoring().requestMatchers(
                    HttpMethod.GET,
                    "/public/**"
            );
            web.ignoring().requestMatchers(
                    HttpMethod.DELETE,
                    "/public/**"
            );
            web.ignoring().requestMatchers(
                    HttpMethod.PUT,
                    "/users/**",
                    "/public/**"
            );
            web.ignoring().requestMatchers(
                            HttpMethod.OPTIONS,
                            "/**"
                    )
                    .requestMatchers("/v3/api-docs/**", "/configuration/**", "/swagger-ui/**",
                            "/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/api-docs/**","http://127.0.0.1:8085/**");

        };
    }

}

