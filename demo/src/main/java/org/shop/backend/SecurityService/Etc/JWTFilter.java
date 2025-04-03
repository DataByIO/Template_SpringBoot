package org.shop.backend.SecurityService.Etc;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.shop.backend.SecurityService.Model.MemberEntity;
import org.shop.backend.SecurityService.Model.RefreshEntity;
import org.shop.backend.SecurityService.Service.RefreshService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

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

    private final RefreshService refreshService;

    public JWTFilter(JWTUtil jwtUtil, RefreshService refreshService) {
        this.jwtUtil = jwtUtil;
        this.refreshService = refreshService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //header에 access라는 명칭으로 토큰을 넣었고 해당 토큰을 가지고 옴
        //String accessToken = request.getHeader("access");

        //get refresh token
        String accessToken = null;
        String refreshToken = null;
        try {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("access")) {
                    accessToken = cookie.getValue();
                }else if (cookie.getName().equals("refresh")) {
                    refreshToken = cookie.getValue();
                }
            }
        }catch (NullPointerException e) {
            filterChain.doFilter(request, response);
            return;
        }

        //access 헤더 검증
        //다음 필터로 넘김합니다 (filterChain.doFilter(request, response)).

        if (accessToken == null) {
            System.out.println("AccessToken NULL :::" + accessToken);
            filterChain.doFilter(request, response);
            //조건이 해당되면 메소드 종료 (필수)
            return;
        }

        //토큰 소멸 시간 검증
        //jwtUtil.isExpired(accessToken)을 통해 토큰이 만료되었는지 확인합니다.
        // 만약 만료된 토큰이라면 요청을 필터 체인에 넘기고, 더 이상 처리하지 않습니다.
        try {
            jwtUtil.isExpired(accessToken);
        } catch (ExpiredJwtException e) {
            //accessToken 만료->refreshToken이 존재하고 refreshToken이 만료가 아닌경우
            if (refreshToken == null) {
                //response status code
                //조건이 해당되면 메소드 종료 (필수)
                return;
            }
            //expired check
            try {
                jwtUtil.isExpired(refreshToken);
                // refrashToken이 만료가 아닌경우
                String category = jwtUtil.getCategory(refreshToken);

                if (!category.equals("refresh")) {
                    //response status code
                    return; // 토큰이 만료될 경우 프론트영역에 어떤 상태코드를 반환할지
                }

                //DB에 refreshToken이 저장되어 있는지 확인
                Boolean isExist = refreshService.existsByRefresh(refreshToken);
                if (!isExist) {
                    //response body
                    //response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 토큰이 만료될 경우 프론트영역에 어떤 상태코드를 반환할지
                    return;
                }

                String username = jwtUtil.getUsername(refreshToken);
                String role = jwtUtil.getRole(refreshToken);
                String id = jwtUtil.getId(refreshToken);

                //make new JWT -> 새로운 AccessCode를 발번해줌
                String newAccess = jwtUtil.createJwt("access", id, username, role, 600000L);
                String newRefresh = jwtUtil.createJwt("refresh", id, username, role, 86400000L);
                //response

                //새로 로그인을 시도 Refresh 토큰 저장 DB에 기존의 Refresh 토큰 삭제 후 새 Refresh 토큰 저장
                refreshService.deleteByRefresh(refreshToken);
                Date date = new Date(System.currentTimeMillis() + 86400000L);
                RefreshEntity refreshEntity  = new RefreshEntity();
                refreshEntity.setId(id);
                refreshEntity.setUsername(username);
                refreshEntity.setRefresh(newRefresh);
                refreshEntity.setExpiration(date.toString());
                refreshService.insertByRefresh(refreshEntity);

                response.addCookie(createCookie("access", newAccess));
                response.addCookie(createCookie("refresh", newRefresh));
            } catch (ExpiredJwtException e2) {
                //response status code
                response.sendRedirect("/loginpage");
            }
            return;

        }

        // 토큰이 accessToken인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(accessToken);

        if (!category.equals("access")) {
            //response body
            return;
        }

        //토큰에서 username과 role 획득
        String id = jwtUtil.getId(accessToken);
        String username = jwtUtil.getUsername(accessToken);
        String role = jwtUtil.getRole(accessToken);

        //userEntity를 생성하여 값 set
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(id);
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

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        //cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}
