package org.shop.backend.SecurityService.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.shop.backend.Member.Model.Member;
import org.shop.backend.Member.Service.MemberService;
import org.shop.backend.SecurityService.Etc.JWTUtil;
import org.shop.backend.SecurityService.Model.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@ResponseBody
public class TestMemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private JWTUtil jwtUtil;

    //유저 정보 불러오기
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

        }
        return new ResponseEntity<>(memberEntity, HttpStatus.OK);
    }

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

   // }
    //FE 영역에서 넘어온 Json 타입의 데이터를 Member라는 Model에 Binding
//    @PostMapping("/경로2/경로2")
//    public ResponseEntity test1(@RequestBody Member member, HttpServletResponse res) {
//
//        return null;
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
