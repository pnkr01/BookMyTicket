package com.fil.TicketBooking.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");

        int statusCode;
        String errorMessage;

        if (authException instanceof BadCredentialsException) {
            statusCode = HttpStatus.UNAUTHORIZED.value();
            errorMessage = "Invalid username or password";
        } else {
            statusCode = HttpStatus.FORBIDDEN.value();
            errorMessage = "Access denied";
        }

        response.setStatus(statusCode);

        Map<String, Object> body = new HashMap<>();
        body.put("status", statusCode);
        body.put("error", HttpStatus.valueOf(statusCode).getReasonPhrase());
        body.put("message", errorMessage);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }
}
