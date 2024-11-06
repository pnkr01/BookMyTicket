package com.fil.TicketBooking.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class AppConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/events/top-sold").permitAll().requestMatchers("/api/events/search/**")
                        .permitAll()
                        .requestMatchers("/api/events/ongoing").permitAll().requestMatchers("/api/events/upcoming").
                        permitAll().requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll())
                .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable);
        return http.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(
                "http://localhost:5173"
        ));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));
        config.setExposedHeaders(List.of("Authorization"));
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

}











//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeHttpRequests(Authorize -> Authorize
//                        .requestMatchers("/api/**").authenticated()
//                        .anyRequest().permitAll()
//                )
//                .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
//                .csrf().disable()
//                .cors().configurationSource(new CorsConfigurationSource() {
//
//                    @Override
//                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//
//                        CorsConfiguration cfg = new CorsConfiguration();
//
//                        cfg.setAllowedOrigins(Arrays.asList(
//
//                                        "http://localhost:3000",
//                                        "http://localhost:4000",
//                                        "http://localhost:4200",
//                                        "https://shopwithzosh.vercel.app",
//                                        "https://ecommerce-angular-blue.vercel.app/"
//
//                                )
//                        );
//                        //cfg.setAllowedMethods(Arrays.asList("GET", "POST","DELETE","PUT"));
//                        cfg.setAllowedMethods(Collections.singletonList("*"));
//                        cfg.setAllowCredentials(true);
//                        cfg.setAllowedHeaders(Collections.singletonList("*"));
//                        cfg.setExposedHeaders(Arrays.asList("Authorization"));
//                        cfg.setMaxAge(3600L);
//                        return cfg;
//
//                    }
//                })
//                .and()
//                .httpBasic()
//                .and()
//                .formLogin(AbstractHttpConfigurer::disable);
//
//        return http.build();