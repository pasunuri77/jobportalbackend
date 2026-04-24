package com.jobportal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .cors(cors -> {
                })

                .authorizeHttpRequests(auth -> auth

                        // ========================
                        // PUBLIC APIs
                        // ========================
                        .requestMatchers(
                                "/api/user/login",
                                "/api/user/register",
                                "/api/user/updatepassword",
                                "api/user/verifyemail",
                                "/uploads/**"
                        ).permitAll()

                        // ========================
                        // USER DELETE (ALLOW ALL ROLES)
                        // ========================
                        .requestMatchers(HttpMethod.DELETE, "/api/user/**")
                        .hasAnyRole("ADMIN", "USER", "COMPANY")

                        // ========================
                        // USER APIs (ADMIN ONLY)
                        // ========================
                        .requestMatchers("/api/user/**")
                        .hasRole("ADMIN")

                        // ========================
                        // JOB APIs
                        // ========================
                        .requestMatchers(HttpMethod.GET, "/api/job/**")
                        .hasAnyRole("USER", "COMPANY", "ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/api/job/update/**")
                        .hasAnyRole("COMPANY", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/job/create")
                        .hasRole("COMPANY")

                        .requestMatchers(HttpMethod.DELETE, "/api/job/**")
                        .hasAnyRole("ADMIN", "COMPANY")

                        // ========================
                        // APPLICATION APIs
                        // ========================
                        .requestMatchers(HttpMethod.POST, "/api/application/apply")
                        .hasRole("USER")

                        .requestMatchers(HttpMethod.PUT, "/api/application/status/**")
                        .hasAnyRole("COMPANY", "ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/application/company/**")
                        .hasAnyRole("COMPANY", "ADMIN")

                        .requestMatchers(HttpMethod.GET,"/api/application/user")
                        .hasAnyRole("USER", "ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/api/application/**")
                        .hasRole("ADMIN")

                        // ========================
                        // COMPANY APIs
                        // ========================
                        .requestMatchers(HttpMethod.GET, "/api/company/**")
                        .hasAnyRole("USER", "COMPANY", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/company/create/**")
                        .hasAnyRole("ADMIN","COMPANY")

                        .requestMatchers(HttpMethod.DELETE, "/api/company/**")
                        .hasRole("ADMIN")

                        // ========================
                        // ANY OTHER
                        // ========================
                        .anyRequest().authenticated()
                )

                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}