package org.shop.backend.SecurityService.Repository;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*************************************************************
 /* SYSTEM NAME      : service
 /* PROGRAM NAME     : JwtServiceImpl.class
 /* DESCRIPTION      : JwtServiceImpl
 https://brunch.co.kr/@jinyoungchoi95/1
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.03.24   KIMDONGMIN   INTIAL RELEASE
 /*2025.03.24   KIMDONGMIN   로그인 체크로직 보안 (getClaims 추가)
 /*************************************************************/

@Service
public class JwtServiceImpl implements JwtService {

    private String secretkey = "abbbasd!$!$!@$14124124124jhiuh2&^*3sdjsdjhiuh2&^*387!98ahiusdjhiuh2&^*3812#!@##@!#7sdjhiuh2&^*387!98a!98ah2&^*387!98a87!98assabzx@#!&(!#k009jadjkbas!@";

    @Override
    public String getToken(String key, Object value) {

        Date expTime = new Date();
        expTime.setTime(expTime.getTime() + 1000 * 60 * 30);

        byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretkey);
        Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS256.getJcaName());

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("typ", "JWT");
        headerMap.put("alg", "HS256");

        Map<String, Object> map = new HashMap<>();
        map.put(key, value);

        JwtBuilder builder = Jwts.builder().setHeaderParams(headerMap)
                .setClaims(map)
                .setExpiration(expTime)
                .signWith(signKey, SignatureAlgorithm.HS256);

        return builder.compact();
    }

    @Override
    public Claims getClaims(String token) {
        // null이 아니고, 토큰 값이 비어있지 않으면
        if (token != null && !token.isEmpty()) {
            try {
                byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretkey);
                Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS256.getJcaName());
                return Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token).getBody();

            } catch (ExpiredJwtException e) {
                // 토큰 만료됨
            } catch (JwtException e) {
                // 토크이 유효이 유효하지 않을때
            }
        }
        return null;
    }

}
