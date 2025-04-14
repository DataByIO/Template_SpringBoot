package org.project.backend.SecurityService.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.backend.SecurityService.Service.MemberService;
import org.project.backend.SecurityService.Etc.JWTUtil;
import org.project.backend.SecurityService.Model.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/*************************************************************
 /* SYSTEM NAME      : controller
 /* PROGRAM NAME     : LoginCheckController.class
 /* DESCRIPTION      :
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.03.25   KIMDONGMIN   INTIAL RELEASE
 /*************************************************************/

@RestController
public class LoginCheckController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private JWTUtil jwtUtil;

    //유저 정보 불러오기
    @ResponseBody
    @PostMapping("/api/account/loginCheck")
    public ResponseEntity<?> login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String accessToken = null;
        MemberEntity memberEntity = new MemberEntity();
        try {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("access")) {
                    accessToken = cookie.getValue();
                }
            }
            //조회한 유저의 컬럼ID를 가져옴 (컬럼ID의 값임.)

            String id = jwtUtil.getId(accessToken);
            String username = jwtUtil.getUsername(accessToken);
            String role = jwtUtil.getRole(accessToken);

            memberEntity.setId(id);
            memberEntity.setUsername(username);
            memberEntity.setRole(role);

        }catch (NullPointerException ignored) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return new ResponseEntity<>(memberEntity, HttpStatus.OK);
    }

}