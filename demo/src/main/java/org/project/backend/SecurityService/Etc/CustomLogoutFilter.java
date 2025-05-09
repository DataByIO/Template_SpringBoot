package org.project.backend.SecurityService.Etc;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.backend.SecurityService.Service.RefreshService;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

/*************************************************************
 /* SYSTEM NAME      : SecurityService/Etc
 /* PROGRAM NAME     : CustomLogoutFilter.java
 /* DESCRIPTION      :
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.03.24   KIMDONGMIN   INTIAL RELEASE
 /*2025.03.31   KIMDONGMIN   흐름예시
 /*2025.04.14   KIMDONGMIN   소스 코드 수정
 /*************************************************************/

public class CustomLogoutFilter extends GenericFilterBean {

    private final JWTUtil jwtUtil;
    private final RefreshService refreshService;

    public CustomLogoutFilter(JWTUtil jwtUtil, RefreshService refreshService) {

        this.jwtUtil = jwtUtil;
        this.refreshService = refreshService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        //path and method verify
        String requestUri = request.getRequestURI();
        if (!requestUri.matches("^\\/logout$")) {

            filterChain.doFilter(request, response);
            return;
        }
        String requestMethod = request.getMethod();
        if (!requestMethod.equals("POST")) {

            filterChain.doFilter(request, response);
            return;
        }

        //get refresh token
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {

            if (cookie.getName().equals("refresh")) {

                refresh = cookie.getValue();
            }
        }

        //refresh null check
        if (refresh == null) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        //expired check
        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {

            //response status code
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // 토큰이 refresh인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(refresh);
        if (!category.equals("refresh")) {

            //response status code
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        //DB에 저장되어 있는지 확인
        Boolean isExist = refreshService.existsByRefresh(refresh);
        if (!isExist) {

            //response status code
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        //로그아웃 진행
        //Refresh 토큰 DB에서 제거
        refreshService.deleteByRefresh(refresh);

        //Refresh 토큰 Cookie 값 0
        Cookie accessCookie = new Cookie("access", null);
        Cookie refreshCookie = new Cookie("refresh", null);
        accessCookie.setMaxAge(0);
        refreshCookie.setMaxAge(0);
        accessCookie.setPath("/");
        refreshCookie.setPath("/");

        response.addCookie(accessCookie);
        response.addCookie(refreshCookie);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}