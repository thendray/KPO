package ru.thendray.restaurantapi.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.thendray.restaurantapi.exceptions.BadRequestException;
import ru.thendray.restaurantapi.jwt.JwtExtractService;
import ru.thendray.restaurantapi.jwt.JwtValidator;
import ru.thendray.restaurantapi.services.UserService;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final String authorizationHeader;
    private final JwtExtractService jwtExtractService;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final JwtValidator jwtValidator;

    public JwtAuthenticationFilter(
            JwtExtractService jwtExtractService,
            UserDetailsService userDetailsService,
            UserService userService,
            JwtValidator jwtValidator,
            @Value("${jwt.header}") String authorizationHeader) {

        this.authorizationHeader = authorizationHeader;
        this.jwtExtractService = jwtExtractService;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.jwtValidator = jwtValidator;
    }


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {


        final String authHeader = request.getHeader(authorizationHeader);
        final String jwt;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);

        String email = jwtExtractService.extractEmail(jwt);

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            var user = userDetailsService.loadUserByUsername(email);
            var userEntity = userService.getUserByEmailOrThrowException(email);

            if (jwtValidator.isTokenValid(jwt, userEntity)) {

                UsernamePasswordAuthenticationToken passwordToken = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                );

                passwordToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(passwordToken);
            }

            filterChain.doFilter(request, response);

        }

    }
}
