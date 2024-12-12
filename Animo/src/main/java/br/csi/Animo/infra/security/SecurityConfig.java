package br.csi.Animo.infra.security;

import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(crsf-> crsf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/Animo/api/animes").permitAll()
                        .requestMatchers("/Animo/api/animes/**").permitAll()

                        .requestMatchers("/Animo/api/animes/**").authenticated()
                        .anyRequest().authenticated()


                        .requestMatchers( "/login").permitAll()
                        .requestMatchers("/avaliacao").permitAll()
                        .requestMatchers( "/genero").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/genero").permitAll()
                        .requestMatchers( "/filme").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET,"/filme").permitAll()
                        .requestMatchers( "/listas").permitAll()
                        .requestMatchers("/usuario").permitAll()

                ).addFilterBefore(this.autenticacaoFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws Exception{
        return configuration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
