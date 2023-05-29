package ru.thendray.restaurantapi.jwt;

import org.springframework.stereotype.Component;
import ru.thendray.restaurantapi.entities.UserEntity;


import java.util.Date;

@Component
public class JwtValidator {


    private final JwtExtractService jwtExtractService;

    public JwtValidator(JwtExtractService jwtExtractService) {
        this.jwtExtractService = jwtExtractService;
    }

    public boolean isTokenValid(String token, UserEntity user) {

        var id = jwtExtractService.extractId(token);

        return (id.equals(user.getId()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return jwtExtractService.extractExpiration(token).before(new Date());
    }

}
