package org.shop.backend.SecurityService.Etc;

import io.jsonwebtoken.ExpiredJwtException;
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
import java.io.PrintWriter;

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
 /*2025.04.01   KIMDONGMIN   Token 검증영역 추가
 /*************************************************************/

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //header에 access라는 명칭으로 토큰을 넣었고 해당 토큰을 가지고 옴
        String accessToken = request.getHeader("access");

        //Authorization 헤더 검증
        //다음 필터로 넘김합니다 (filterChain.doFilter(request, response)).
        if (accessToken == null) {
            System.out.println("AccessToken NULL :::" + accessToken);
            filterChain.doFilter(request, response);
            //조건이 해당되면 메소드 종료 (필수)
            return;
        }

        //토큰 소멸 시간 검증
        //jwtUtil.isExpired(token)을 통해 토큰이 만료되었는지 확인합니다.
        // 만약 만료된 토큰이라면 요청을 필터 체인에 넘기고, 더 이상 처리하지 않습니다.
        if (jwtUtil.isExpired(accessToken)) {
            System.out.println("token expired");
            filterChain.doFilter(request, response);
            //조건이 해당되면 메소드 종료 (필수)
            return;
        }
        // 토큰 만료 여부 확인, 만료시 다음 필터로 넘기지 않음
        try {
            jwtUtil.isExpired(accessToken);
        } catch (ExpiredJwtException e) {

            //response body
            PrintWriter writer = response.getWriter();
            writer.print("access token expired");

            //response status code
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 토큰이 만료될 경우 프론트영역에 어떤 상태코드를 반환할지
            return;
        }

        // 토큰이 accessToken인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(accessToken);

        if (!category.equals("access")) {

            //response body
            PrintWriter writer = response.getWriter();
            writer.print("invalid access token");

            //response status code
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //토큰에서 username과 role 획득
        String username = jwtUtil.getUsername(accessToken);
        String role = jwtUtil.getRole(accessToken);

        //userEntity를 생성하여 값 set
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUsername(username);
        memberEntity.setRole(role);
        //UserDetails에 회원 정보 객체 담기
        CustomUserDetailsServiceImpl customUserDetails = new CustomUserDetailsServiceImpl(memberEntity);

        //스프링 시큐리티 인증 토큰 생성 -> UsernamePasswordAuthenticationToken은 Spring Security에서 인증을 위한 토큰 객체입니다.
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        //SecurityContextHolder는 Spring Security에서 인증 정보를 저장하고 관리하는 컨테이너 역할을 합니다. ->
        //SecurityContextHolder에 인증 정보를 저장하여, 이후 Spring Security가 이 인증 정보를 사용하여 요청을 처리하도록 합니다.
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
