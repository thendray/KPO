package ru.thendray.authorizationapi.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.thendray.authorizationapi.entities.UserEntity;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    private final String secretKey;

    public JwtProvider(@Value("${jwt.secret-key}") String secretKey) {
        this.secretKey = secretKey;
    }

    private Key getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String generateJwt(UserEntity user) {
        Map<String, Object> customClaims = new HashMap<>();
        customClaims.put("email", user.getEmail());
        customClaims.put("id", user.getId());

        Date dFinish = Date.from(Instant.now().plusSeconds(100000));
        Date dStart = Date.from(Instant.now());

        return Jwts
                .builder()
                .setClaims(customClaims)
                .setIssuedAt(dStart)
                .setExpiration(dFinish)
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();

    }



}
