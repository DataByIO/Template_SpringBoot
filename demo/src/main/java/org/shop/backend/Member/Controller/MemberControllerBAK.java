package org.shop.backend.Member.Controller;

import jakarta.servlet.http.HttpServletResponse;
import org.shop.backend.Member.Model.Member;

import org.shop.backend.Member.Service.MemberService;
import org.shop.backend.SecurityService.Etc.JWTUtil;
import org.shop.backend.SecurityService.Model.MemberEntity;
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
public class MemberControllerBAK {

//    @Autowired
//    private MemberService memberService;
//
//    @Autowired
//    private JWTUtil jwtUtil;
//
//    유저 정보 불러오기
//    @PostMapping("/api/account/login")
//    public ResponseEntity login(@RequestBody Member member, HttpServletResponse res) throws Exception {
//        //조회한 유저의 컬럼ID를 가져옴 (컬럼ID의 값임.)
//        MemberEntity memberEntity = new MemberEntity();
//
//        String id = jwtUtil.getId(accessToken);
//        String username = jwtUtil.getUsername(accessToken);
//        String role = jwtUtil.getRole(accessToken);
//
//        memberEntity.setId(id);
//        memberEntity.setUsername(username);
//        memberEntity.setRole(role);
//        return new ResponseEntity<>(memberEntity,HttpStatus.OK);
//    }

    //요청 데이터를 String Object 형식으로 받아옴: Key Value
//    @PostMapping("/경로/경로")
//    public void init(@RequestBody HashMap<String, Object> map) {
//        System.out.println(map);
        //데이터를 꺼내옴
        //String name = map.get("name");
        //int age = map.get("age");
        // name=vita, age=25 출력

        //데이터를 넣어줌(존재하는 Key값이 있다면 수정도 가능)
        //hashMap.put("name", "ohback")
        //name=ohback

        //value를 통해 key 값얻기
        //System.out.println(getKey(hashMap,"rios"));

    //}
    //FE 영역에서 넘어온 Json 타입의 데이터를 Member라는 Model에 Binding
//    @PostMapping("/경로2/경로2")
//    public ResponseEntity test1(@RequestBody Member member, HttpServletResponse res) {
//
//    return null;
//    }
//
//    @GetMapping("/api/account/check")
//    public ResponseEntity check(@CookieValue(value = "token", required = false) String token) {
//        Claims claims = jwtService.getClaims(token);
//
//        if (claims != null) {
//            int id = Integer.parseInt(claims.get("id").toString());
//            return new ResponseEntity<>(id, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(null, HttpStatus.OK);
//    }
}
