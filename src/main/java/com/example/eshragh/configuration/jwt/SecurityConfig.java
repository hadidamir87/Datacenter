package com.example.eshragh.configuration.jwt;


import com.example.eshragh.configuration.jwt.JwtAuthenticationEntryPoint;
import com.example.eshragh.configuration.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalAuthentication



public class SecurityConfig {
    private final JwtAuthenticationEntryPoint unauthorizedHandler;
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/v1/auth/register","/api/v1/auth/login","/user/**") .permitAll()
//                        .requestMatchers("/user/get/{id}").hasAnyAuthority("ROLE_Admin")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
//                .exceptionHandling(exception -> exception
//                        .authenticationEntryPoint(unauthorizedHandler)
//                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, AbstractPreAuthenticatedProcessingFilter.class);
        return http.build();
    }
}
