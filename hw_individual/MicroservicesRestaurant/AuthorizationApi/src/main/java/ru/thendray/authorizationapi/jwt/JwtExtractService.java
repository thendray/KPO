package ru.thendray.authorizationapi.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
@Component
public class JwtExtractService {

    private final String secretKey;

    public JwtExtractService(@Value("${jwt.secret-key}") String secretKey) {
        this.secretKey = secretKey;
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public <T> T extractClaim(String jwtToken, Function<Claims, T> resolver) {
        var claims = extractAllClaims(jwtToken);
        return resolver.apply(claims);
    }

    public String extractEmail(String jwt) {
        var claims = extractAllClaims(jwt);

        return claims.get("email", String.class);
    }

    public Long extractId(String token) {
        var claims = extractAllClaims(token);

        return claims.get("id", Long.class);
    }

    public Date extractExpiration(String token) {

        return extractClaim(token, Claims::getExpiration);
    }
}
