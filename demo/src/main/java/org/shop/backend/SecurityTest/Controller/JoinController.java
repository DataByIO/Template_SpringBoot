package org.shop.backend.SecurityTest.Controller;

import jakarta.servlet.http.HttpServletResponse;
import org.shop.backend.Member.Model.Member;
import org.shop.backend.SecurityTest.Entity.MemberEntity;
import org.shop.backend.SecurityTest.Repository.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinController(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping ("/join")
    //@RequestBody
    public ResponseEntity join(MemberEntity memberEntity, HttpServletResponse res) throws Exception {
        HashMap<String, Object> memberInfo = joinService.memberInfo(memberEntity);

        if(memberInfo!= null) {// 회원 정보가 있는지 체크
            //가입 회원
            return new ResponseEntity<>(memberEntity, HttpStatus.OK);// Password 암호화 체크
        }else {
            //유저 정보가 비어있음 -> 미가입 회원
            //유저 정보를 insert 할 필요가 있음,
            memberEntity.setPassword(bCryptPasswordEncoder.encode(memberEntity.getPassword()));// Password 암호화 후 데이터 교체
            joinService.insertMember(memberEntity);
        }
        return new ResponseEntity<>(memberEntity.getPassword(), HttpStatus.OK);// Password 암호화 체크
    }
}
