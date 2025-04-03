package org.shop.backend.SecurityService.Etc;

import org.shop.backend.SecurityService.Service.RefreshService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

/*************************************************************
 /* SYSTEM NAME      : SecurityService
 /* PROGRAM NAME     : SecurityConfig.java
 /* DESCRIPTION      :
 1. 클라이언트가 요청을 보낼 때, JWT 토큰을 HTTP 헤더에 포함하여 보냅니다.
 2. Spring Security 필터 체인에서 JWTFilter가 먼저 실행되어, 요청에 포함된 JWT 토큰을 검증합니다.
 3. JWT 토큰이 유효하면, 해당 사용자 정보를 SecurityContext에 설정하고 인증이 완료됩니다.
 4. 이후 LoginFilter가 실행되지만, JWTFilter에서 이미 인증 처리가 되었기 때문에,
 로그인 폼 인증은 더 이상 필요하지 않습니다. 대신,
 인증 정보가 정상적으로 설정되어 이후 필터들이 정상적으로 동작합니다.
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.03.24   KIMDONGMIN   INTIAL RELEASE
 /*2025.03.31   KIMDONGMIN   흐름예시
 /*************************************************************/

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    //JWTUtil 주입
    private final JWTUtil jwtUtil;

    private final RefreshService refreshService;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil, RefreshService refreshService) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
        this.refreshService = refreshService;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf((auth) -> auth.disable());
        http.formLogin((auth) -> auth.disable());
        http.httpBasic((auth) -> auth.disable());
        http.authorizeHttpRequests((auth) -> auth
            .requestMatchers("/login", "/", "/join", "/check", "/api/*", "/api/items/*", "/api/account/*","/reissue", "/main").permitAll()
            .requestMatchers("/admin").hasRole("ADMIN"));
        //토근을 검증하기 위한 Filter를 설정한다.
        http.addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);
        //AuthenticationManager()와 JWTUtil 인수 전달
        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil, refreshService), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new CustomLogoutFilter(jwtUtil, refreshService), LogoutFilter.class);
        http.sessionManagement((session) -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}