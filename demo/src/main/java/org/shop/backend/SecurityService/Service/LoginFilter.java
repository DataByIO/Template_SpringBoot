package org.shop.backend.SecurityService.Service;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Collection;
import java.util.Iterator;

/*************************************************************
 /* SYSTEM NAME      : SecurityService/Service
 /* PROGRAM NAME     : LoginFilter.java
 /* DESCRIPTION      :
 사용자가 로그인 요청을 보냄:
 1. 클라이언트는 HTTP 요청에 username과 password를 포함하여 서버로 보냅니다.
 2. attemptAuthentication 메서드 실행:
 LoginFilter는 요청에서 username과 password를 추출하고,
 이를 UsernamePasswordAuthenticationToken 객체에 담아 authenticationManager.authenticate()로 인증을 시도합니다.
 3.인증 성공 시:
 인증이 성공하면, successfulAuthentication 메서드가 실행되어
 사용자의 username과 role을 바탕으로 JWT 토큰을 생성합니다.
 생성된 JWT 토큰은 Authorization 헤더에 추가되어 클라이언트에게 반환됩니다.
 이후 클라이언트는 이 토큰을 사용하여 인증이 필요한 요청을 보낼 수 있습니다.
 4.인증 실패 시:
 인증이 실패하면 unsuccessfulAuthentication 메서드가 실행되어
 HTTP 상태 코드 401 Unauthorized가 클라이언트에게 반환됩니다.
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.03.24   KIMDONGMIN   INTIAL RELEASE
 /*2025.03.31   KIMDONGMIN   흐름예시
 /*************************************************************/

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    //Spring Security의 인증 매니저로, 사용자 인증을 담당합니다. 로그인 시 제공된 사용자명과 비밀번호를 사용하여 인증을 시도합니다.
    private final AuthenticationManager authenticationManager;
    //JWTUtil 주입
    private final JWTUtil jwtUtil;

    //LoginFilter는 생성자에서 authenticationManager와 jwtUtil을 주입받습니다.
    //이를 통해 인증 관리와 JWT 생성 기능을 사용할 수 있게 됩니다.
    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(@RequestBody HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        //클라이언트 요청에서 username, password 추출
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println(username);
        System.out.println(password);

        //스프링 시큐리티에서 username과 password를 검증하기 위해서는 token에 담아야 함
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);

        //token에 담은 검증을 위한 AuthenticationManager로 전달
        return authenticationManager.authenticate(authToken);
    }


    //JWT를 발급함
    //로그인 성공 시 실행되는 메서드로, 로그인 인증을 통과한 사용자의 정보를 가져옵니다.

    @Override
    protected void successfulAuthentication(@RequestBody HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        
        System.out.println("success");
        CustomUserDetailsServiceImpl customUserDetailsService = (CustomUserDetailsServiceImpl) authentication.getPrincipal();

        String username = customUserDetailsService.getUsername();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String role = auth.getAuthority();
        //사용자의 username과 role(권한)을 추출하고, jwtUtil.createJwt()를 사용하여 JWT 토큰을 생성합니다.
        String token = jwtUtil.createJwt(username, role, 60*60*50L);

        System.out.println("UserName(ID) ::: " + username);
        System.out.println("role(role) ::: " + role);
        System.out.println("token(token) ::: " + token);
        //JWT 토큰은 Authorization 헤더에 "Bearer " + token 형식으로 추가되어 클라이언트에게 반환됩니다.
        response.addHeader("Authorization", "Bearer " + token);
    }

    //로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        System.out.println("failure");
        response.setStatus(401);
    }
}
