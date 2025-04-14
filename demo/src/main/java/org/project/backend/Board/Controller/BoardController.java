package org.project.backend.Board.Controller;

import jakarta.servlet.http.HttpServletResponse;
import org.project.backend.SecurityService.Model.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class BoardController {


    @Autowired
    //private BoardService boardService;

    @PostMapping("/boardSelect")
    //@RequestBody
    public ResponseEntity boardSelect(HttpServletResponse res) throws Exception {
        //HashMap<String, Object> boardService = boardSelect();

        return new ResponseEntity<>(HttpStatus.OK);// Password 암호화 체크
    }

    @PostMapping("/boardInsert")
    //@RequestBody
    public ResponseEntity boardInsert(MemberEntity memberEntity, HttpServletResponse res) throws Exception {
        //HashMap<String, Object> boardService = boardSelect();
        return new ResponseEntity<>(HttpStatus.OK);// Password 암호화 체크
    }

    @PostMapping("/boardDelete")
    //@RequestBody
    public ResponseEntity boardDelete(MemberEntity memberEntity, HttpServletResponse res) throws Exception {
        //HashMap<String, Object> boardService = boardSelect();
        return new ResponseEntity<>(HttpStatus.OK);// Password 암호화 체크
    }

    @PostMapping("/boardUpdate")
    //@RequestBody
    public ResponseEntity boardUpdate(MemberEntity memberEntity, HttpServletResponse res) throws Exception {
        //HashMap<String, Object> boardService = boardSelect();
        return new ResponseEntity<>(HttpStatus.OK);// Password 암호화 체크
    }


}
