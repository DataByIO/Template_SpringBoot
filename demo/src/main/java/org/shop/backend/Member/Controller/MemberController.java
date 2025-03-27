package org.shop.backend.Member.Controller;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.shop.backend.Member.Model.Member;

import org.shop.backend.Member.Service.MemberService;
import org.shop.backend.SecurityService.Repository.JwtService;
import org.shop.backend.SecurityService.Repository.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;

/*************************************************************
 /* SYSTEM NAME      : controller
 /* PROGRAM NAME     : MemberController.class
 /* DESCRIPTION      :
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.03.24   KIMDONGMIN   INTIAL RELEASE
 /*************************************************************/

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    JwtService jwtService;

    //유저 정보 불러오기
    @PostMapping("/api/account/login")
    public ResponseEntity login(@RequestBody Member member, HttpServletResponse res) throws Exception {
        HashMap<String, Object> userInfoMap = memberService.userInfo(member);
        //조회한 유저의 컬럼ID를 가져옴 (컬럼ID의 값임.)
        if(userInfoMap.get("id") != null) {
//            JwtService jwtService = new JwtServiceImpl();
//            int id = (int) userInfoMap.get("id");
//            String token = jwtService.getToken("id", id);// ID값이 있을때 Token을 생성함
//            Cookie cookie = new Cookie("token", token);
//            cookie.setHttpOnly(true);
//            cookie.setPath("/");
//
//            res.addCookie(cookie);

            return new ResponseEntity<>(id, HttpStatus.OK);
        }


        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    //요청 데이터를 String Object 형식으로 받아옴: Key Value
    @PostMapping("/경로/경로")
    public void init(@RequestBody HashMap<String, Object> map) {
        System.out.println(map);
        //데이터를 꺼내옴
        //String name = map.get("name");
        //int age = map.get("age");
        // name=vita, age=25 출력

        //데이터를 넣어줌(존재하는 Key값이 있다면 수정도 가능)
        //hashMap.put("name", "ohback")
        //name=ohback

        //value를 통해 key 값얻기
        //System.out.println(getKey(hashMap,"rios"));

    }
    //FE 영역에서 넘어온 Json 타입의 데이터를 Member라는 Model에 Binding
    @PostMapping("/경로2/경로2")
    public ResponseEntity test1(@RequestBody Member member, HttpServletResponse res) {

    return null;
    }

    @GetMapping("/api/account/check")
    public ResponseEntity check(@CookieValue(value = "token", required = false) String token) {
        Claims claims = jwtService.getClaims(token);

        if (claims != null) {
            int id = Integer.parseInt(claims.get("id").toString());
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
