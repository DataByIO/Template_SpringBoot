package org.project.backend.SecurityService.Etc;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.text.SimpleDateFormat;
import java.util.Date;

/*************************************************************
 /* SYSTEM NAME      : SecurityService/Etc
 /* PROGRAM NAME     : JWTUtil.java
 /* DESCRIPTION      :
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.03.24   KIMDONGMIN   INTIAL RELEASE
 /*2025.03.31   KIMDONGMIN   흐름예시
 /*2025.04.14   KIMDONGMIN   소스 코드 수정
 /*************************************************************/

@Component
public class JWTUtil {

    private final SecretKey secretKey;

    public JWTUtil(@Value("${spring.jwt.secret}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    // ✅ 공통 Claims 파싱 메서드
    private Claims extractClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new RuntimeException("Invalid JWT token", e);
        }
    }

    public String getId(String token) {
        return extractClaims(token).get("id", String.class);
    }

    public String getUsername(String token) {
        return extractClaims(token).get("username", String.class);
    }

    public String getRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    public String getCategory(String token) {
        return extractClaims(token).get("category", String.class);
    }

    // ✅ 명확하게 boolean 리턴하도록 개선
    public boolean isExpired(String token) {
        try {
            Date expiration = extractClaims(token).getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true; // 유효하지 않은 토큰은 만료로 간주
        }
    }

    public String createJwt(String category, String id, String username, String role, Long expiredMs) {
        Claims claims = Jwts.claims();
        claims.put("category", category);
        claims.put("id", id);
        claims.put("username", username);
        claims.put("role", role);

        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expiredMs))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }
}
