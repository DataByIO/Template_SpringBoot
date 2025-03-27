package org.shop.backend.SecurityService.Repository;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

/*************************************************************
 /* SYSTEM NAME      : service
 /* PROGRAM NAME     : JwtService.class
 /* DESCRIPTION      : JwtService
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.03.24   KIMDONGMIN   INTIAL RELEASE
 /*************************************************************/
@Service
public interface JwtService {
    String getToken(String key, Object value);
    Claims getClaims(String token);
}
