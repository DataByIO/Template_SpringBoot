package org.shop.backend.SecurityTest.Controller;

import jakarta.servlet.http.Cookie;
import org.shop.backend.Member.Service.MemberService;
import org.shop.backend.SecurityService.JwtService;
import org.shop.backend.SecurityService.JwtServiceImpl;
import org.shop.backend.SecurityTest.Entity.MemberEntity;
import org.shop.backend.SecurityTest.Repository.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;

/*************************************************************
 /* SYSTEM NAME      : controller
 /* PROGRAM NAME     : JoinController.class
 /* DESCRIPTION      :
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.03.25   KIMDONGMIN   INTIAL RELEASE
 /*2025.03.25   KIMDONGMIN   웹 동작이 아닌 API성 동작을 기반으로 제작, 이후 웹 동작으로 변경 할 예정
 /*************************************************************/

@Controller
@ResponseBody
public class JoinController {

    @Autowired
    private JoinService joinService;

    @GetMapping("/join")
    public ResponseEntity joinProcess(@RequestBody MemberEntity memberEntity) throws Exception {
        HashMap<String, Object> userInfoMap = joinService.memberInfo(memberEntity);
        //조회한 유저의 컬럼ID를 가져옴 (컬럼ID의 값임.)
        if(userInfoMap.get("id") != null && userInfoMap.get("password") != null) {
            //가입 회원
        }else {
            //미가입 회원

        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
