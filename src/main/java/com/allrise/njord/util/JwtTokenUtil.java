package com.allrise.njord.util;

import com.allrise.njord.entity.UserEntity;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtTokenUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000;

    private static final SecretKey SECRET_KEY = Jwts.SIG.HS512.key().build();

    public String generateAccessToken(UserEntity userEntity) {
        return Jwts.builder()
                .subject(String.format("%s,%s", userEntity.getId(), userEntity.getEmail()))
                .issuer("NJORD")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SECRET_KEY)
                .compact();
    }

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException ex) {
            LOGGER.error("JWT expired ");
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Token is null, empty or only whitespace");
        } catch (MalformedJwtException ex) {
            LOGGER.error("JWT is invalid");
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("JWT is not supported");
        } catch (SecurityException ex) {
            LOGGER.error("Signature validation failed");
        } catch (JwtException ex) {
            LOGGER.error("Signature2 validation failed");
        }

        return false;
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
