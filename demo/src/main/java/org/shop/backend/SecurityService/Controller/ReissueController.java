package org.shop.backend.SecurityService.Controller;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.shop.backend.Member.Service.MemberService;
import org.shop.backend.SecurityService.Model.MemberEntity;
import org.shop.backend.SecurityService.Model.RefreshEntity;
import org.shop.backend.SecurityService.Etc.JWTUtil;
import org.shop.backend.SecurityService.Service.RefreshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Controller
@ResponseBody
public class ReissueController {

    @Autowired
    private RefreshService refreshService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
//
//        //get refresh token
//        String refresh = null;
//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie : cookies) {
//
//            if (cookie.getName().equals("refresh")) {
//
//                refresh = cookie.getValue();
//            }
//        }
//
//        if (refresh == null) {
//            //response status code
//            return new ResponseEntity<>("refresh token null", HttpStatus.BAD_REQUEST);
//        }
//
//        //expired check
//        try {
//            jwtUtil.isExpired(refresh);
//        } catch (ExpiredJwtException e) {
//
//            //response status code
//            return new ResponseEntity<>("refresh token expired", HttpStatus.BAD_REQUEST);
//        }
//
//        // 토큰이 refresh인지 확인 (발급시 페이로드에 명시)
//        String category = jwtUtil.getCategory(refresh);
//
//        if (!category.equals("refresh")) {
//            //response status code
//            return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
//        }
//
//        //DB에 refreshToken이 저장되어 있는지 확인
//        Boolean isExist = refreshService.existsByRefresh(refresh);
//        if (!isExist) {
//            //response body
//            return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
//        }
//
//        String username = jwtUtil.getUsername(refresh);
//        String role = jwtUtil.getRole(refresh);
//        String id = jwtUtil.getId(refresh);
//
//        //make new JWT -> 새로운 AccessCode를 발번해줌
//        String newAccess = jwtUtil.createJwt("access", id, username, role, 600000L);
//        String newRefresh = jwtUtil.createJwt("refresh", id, username, role, 86400000L);
//        //response
//
//        //새로 로그인을 시도 Refresh 토큰 저장 DB에 기존의 Refresh 토큰 삭제 후 새 Refresh 토큰 저장
//        refreshService.deleteByRefresh(refresh);
//        Date date = new Date(System.currentTimeMillis() + 86400000L);
//        RefreshEntity refreshEntity  = new RefreshEntity();
//        String userid = refreshEntity.getId();
//        refreshEntity.setUsername(username);
//        refreshEntity.setRefresh(newRefresh);
//        refreshEntity.setExpiration(date.toString());
//        refreshService.insertByRefresh(refreshEntity);
//
//        //Refresh 토큰 저장 DB에 기존의 Refresh 토큰 삭제 후 새 Refresh 토큰 저장
//
//
//        //Refresh 토큰 교체로 보안성 강화 로그인 지속시간 길어짐
//        response.addCookie(createCookie("access", newAccess));
//        response.addCookie(createCookie("refresh", newRefresh)); //Refresh Rotate -> Access 토큰 갱신 시 Refresh 토큰도 함께 갱신하는 방법
//
//        return new ResponseEntity<>(HttpStatus.OK);
//
//
//    private Cookie createCookie(String key, String value) //{
//        Cookie cookie = new Cookie(key, value);
//        cookie.setMaxAge(24*60*60);
//        //cookie.setSecure(true);
//        //cookie.setPath("/");
//        cookie.setHttpOnly(true);
//
//        return cookie;
    return null;
    }
}