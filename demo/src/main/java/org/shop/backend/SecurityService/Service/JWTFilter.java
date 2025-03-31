package org.shop.backend.SecurityService.Service;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.shop.backend.SecurityService.Model.MemberEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*************************************************************
 /* SYSTEM NAME      : SecurityService/Service
 /* PROGRAM NAME     : LoginFilter.java
 /* DESCRIPTION      :
 JWT 토큰 추출 및 검증: JWTFilter는 Authorization 헤더에서 JWT 토큰을 추출하고,
 그 유효성을 검증합니다. 유효한 토큰일 경우 토큰에서 사용자 정보(사용자명, 역할)를 추출합니다.
 인증 설정: 토큰이 유효하고 만료되지 않았다면,
 CustomUserDetailsServiceImpl 객체를 사용하여 UsernamePasswordAuthenticationToken을 생성하고,
 이를 SecurityContext에 설정하여 이후 필터들이 인증된 사용자의 정보를 사용할 수 있도록 합니다.
 토큰 만료 처리: 만약 토큰이 만료되었거나 Authorization 헤더가 잘못되었으면,
 요청을 필터 체인에 전달하며 인증을 진행하지 않습니다.
 이 필터는 JWT 기반의 인증을 처리하며, 사용자 인증 정보를 설정하는 역할을 하여,
 보안 필터 체인에서 JWT 인증을 완료한 후 다른 요청 처리 로직으로 넘어갈 수 있도록 합니다.
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.03.24   KIMDONGMIN   INTIAL RELEASE
 /*2025.03.31   KIMDONGMIN   흐름예시
 /*************************************************************/

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //request에서 Authorization 헤더를 찾음
        String authorization= request.getHeader("Authorization");

        //Authorization 헤더 검증
        //Authorization 헤더가 없거나 Bearer 로 시작하지 않으면 토큰이 없다고 판단하고,
        // 요청을 다음 필터로 전달합니다 (filterChain.doFilter(request, response)).
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            System.out.println("token null");
            filterChain.doFilter(request, response);
            //조건이 해당되면 메소드 종료 (필수)
            return;
        }

        System.out.println("authorization now");
        //Bearer 부분 제거 후 순수 토큰만 획득
        String token = authorization.split(" ")[1];

        //토큰 소멸 시간 검증
        //jwtUtil.isExpired(token)을 통해 토큰이 만료되었는지 확인합니다.
        // 만약 만료된 토큰이라면 요청을 필터 체인에 넘기고, 더 이상 처리하지 않습니다.
        if (jwtUtil.isExpired(token)) {
            System.out.println("token expired");
            filterChain.doFilter(request, response);
            //조건이 해당되면 메소드 종료 (필수)
            return;
        }

        //토큰에서 username과 role 획득
        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        //userEntity를 생성하여 값 set
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUsername(username);
        memberEntity.setRole(role);
        memberEntity.setPassword("temppassword");

        //UserDetails에 회원 정보 객체 담기
        CustomUserDetailsServiceImpl customUserDetails = new CustomUserDetailsServiceImpl(memberEntity);

        //스프링 시큐리티 인증 토큰 생성 -> UsernamePasswordAuthenticationToken은 Spring Security에서 인증을 위한 토큰 객체입니다.
        System.out.println(" customUserDetails.getAuthorities() ::: " +  customUserDetails.getAuthorities().toString());
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        //SecurityContextHolder는 Spring Security에서 인증 정보를 저장하고 관리하는 컨테이너 역할을 합니다. ->
        //SecurityContextHolder에 인증 정보를 저장하여, 이후 Spring Security가 이 인증 정보를 사용하여 요청을 처리하도록 합니다.
        SecurityContextHolder.getContext().setAuthentication(authToken);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication.getName(); :::" + authentication.getName());

        filterChain.doFilter(request, response);
    }
}
