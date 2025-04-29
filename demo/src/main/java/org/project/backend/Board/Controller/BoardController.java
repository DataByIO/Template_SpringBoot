package org.project.backend.Board.Controller;

import jakarta.servlet.http.HttpServletResponse;
import org.project.backend.Board.Model.BoardEntity;
import org.project.backend.Board.Service.BoardService;
import org.project.backend.Board.Service.BoardServiceImpl;
import org.project.backend.SecurityService.Model.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/*************************************************************
 /* SYSTEM NAME      : Controller
 /* PROGRAM NAME     : BoardController.class
 /* DESCRIPTION      :
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.04.14   KIMDONGMIN   INTIAL RELEASE
 /*2025.04.18   KIMDONGMIN   INTIAL RELEASE
 /*************************************************************/

@RestController
public class BoardController {

    @Autowired
    private BoardServiceImpl boardServiceImpl;

    @PostMapping("/boardSelect")
    public ResponseEntity boardSelect(BoardEntity boardEntity) throws Exception {

        boardServiceImpl.selectBoard(boardEntity);
        return ResponseEntity.ok(boardEntity);// Password 암호화 체크
    }

    @PostMapping("/boardInsert")
    public ResponseEntity boardInsert(MemberEntity memberEntity, HttpServletResponse res) throws Exception {
        //HashMap<String, Object> boardService = boardSelect();
        return new ResponseEntity<>(HttpStatus.OK);// Password 암호화 체크
    }

    @PostMapping("/boardUpdate")
    public ResponseEntity boardUpdate(MemberEntity memberEntity, HttpServletResponse res) throws Exception {
        //HashMap<String, Object> boardService = boardSelect();
        return new ResponseEntity<>(HttpStatus.OK);// Password 암호화 체크
    }

    @PostMapping("/boardDelete")
    public ResponseEntity boardDelete(MemberEntity memberEntity, HttpServletResponse res) throws Exception {
        //HashMap<String, Object> boardService = boardSelect();
        return new ResponseEntity<>(HttpStatus.OK);// Password 암호화 체크
    }


}
