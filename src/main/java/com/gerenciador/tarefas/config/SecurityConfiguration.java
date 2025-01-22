package com.gerenciador.tarefas.config;

import com.gerenciador.tarefas.filter.AuthenticationFilter;
import com.gerenciador.tarefas.filter.LoginFilter;
import com.gerenciador.tarefas.permissions.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/login").permitAll()
                            .requestMatchers(HttpMethod.GET, "/teste-api").permitAll()
                            .requestMatchers(HttpMethod.GET, "/teste-api-bem-vindo").hasAuthority(RoleEnum.ADMIN.toString())
                            .requestMatchers(HttpMethod.GET, "/users").hasAuthority(RoleEnum.USER.toString())
                            .requestMatchers(HttpMethod.POST, "/users").hasAuthority(RoleEnum.ADMIN.toString())
                            .requestMatchers(HttpMethod.POST, "/task-manager").hasAuthority(RoleEnum.ADMIN.toString())
                            .anyRequest()
                            .authenticated();
                });

        http.addFilterBefore(new LoginFilter("/login", authenticationConfiguration.getAuthenticationManager()), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
}