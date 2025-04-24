package org.project.backend.SecurityService.Etc;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.project.backend.SecurityService.Model.MemberEntity;
import org.project.backend.SecurityService.Model.RefreshEntity;
import org.project.backend.SecurityService.Service.RefreshService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

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
@Slf4j
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final RefreshService refreshService;

    public JWTFilter(JWTUtil jwtUtil, RefreshService refreshService) {
        this.jwtUtil = jwtUtil;
        this.refreshService = refreshService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String accessToken = null;
        String refreshToken = null;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                switch (cookie.getName()) {
                    case "access" -> accessToken = cookie.getValue();
                    case "refresh" -> refreshToken = cookie.getValue();
                }
            }
        }

        // AccessToken 없으면 인증 불가 → 401 응답
        if (accessToken == null) {
            log.debug("AccessToken is missing.");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Access token required.");
            return;
        }

        // AccessToken 만료
        if (jwtUtil.isExpired(accessToken)) {
            log.debug("AccessToken expired.");
            if (!handleAccessTokenExpired(refreshToken, response)) {
                return; // Refresh 실패한 경우만 중단
            }
            // Refresh 성공한 경우 → 다음 필터로 넘겨야 함
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰 유형 검사
        String category = Optional.ofNullable(jwtUtil.getCategory(accessToken)).orElse("");
        if (!"access".equals(category)) {
            log.debug("Invalid token category: {}", category);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid access token.");
            return;
        }

        // 정상 토큰이면 인증 컨텍스트 설정 후 필터 체인 진행
        setupAuthenticationContext(accessToken);
        filterChain.doFilter(request, response);
    }

    private boolean handleAccessTokenExpired(String refreshToken, HttpServletResponse response) throws IOException {
        if (refreshToken == null || jwtUtil.isExpired(refreshToken)) {
            log.debug("Refresh token is missing or expired.");
            response.sendRedirect("/loginpage");
            return false;
        }

        String category = Optional.ofNullable(jwtUtil.getCategory(refreshToken)).orElse("");
        if (!"refresh".equals(category)) {
            log.debug("Invalid refresh token category.");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid refresh token.");
            return false;
        }

        if (!refreshService.existsByRefresh(refreshToken)) {
            log.debug("Refresh token not found in DB.");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Refresh token not recognized.");
            return false;
        }

        // 새 토큰 발급
        String username = jwtUtil.getUsername(refreshToken);
        String role = jwtUtil.getRole(refreshToken);
        String id = jwtUtil.getId(refreshToken);

        String newAccess = jwtUtil.createJwt("access", id, username, role, 600000L);
        String newRefresh = jwtUtil.createJwt("refresh", id, username, role, 86400000L);

        // DB 갱신
        refreshService.deleteByRefresh(refreshToken);
        // 만료일 설정 (1일 후)
        Date expiration = new Date(System.currentTimeMillis() + 86400000L);
        String expirationStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(expiration);

        // RefreshEntity 생성 및 저장
        RefreshEntity refreshEntity = new RefreshEntity();
        refreshEntity.setId(id);
        refreshEntity.setUsername(username);
        refreshEntity.setRefresh(newRefresh);
        refreshEntity.setExpiration(expirationStr);

        refreshService.insertByRefresh(refreshEntity);


        // 쿠키 교체
        response.addCookie(createCookie("access", newAccess));
        response.addCookie(createCookie("refresh", newRefresh));

        // 인증 컨텍스트도 새로 설정
        setupAuthenticationContext(newAccess);
        return true;
    }

    private void setupAuthenticationContext(String token) {
        String id = jwtUtil.getId(token);
        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        MemberEntity member = new MemberEntity();
        member.setId(id);
        member.setUsername(username);
        member.setRole(role);

        CustomUserDetailsServiceImpl userDetails = new CustomUserDetailsServiceImpl(member);
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        return cookie;
    }
}
