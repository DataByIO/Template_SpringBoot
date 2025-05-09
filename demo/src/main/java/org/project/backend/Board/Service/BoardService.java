package org.project.backend.Board.Service;

import org.project.backend.Board.Model.BoardEntity;
import org.project.backend.SecurityService.Model.MemberEntity;

import java.util.HashMap;

/*************************************************************
 /* SYSTEM NAME      : Service
 /* PROGRAM NAME     : BoardService.class
 /* DESCRIPTION      :
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.04.14   KIMDONGMIN   INTIAL RELEASE
 /*************************************************************/

public interface BoardService {
    public HashMap<String, Object> selectBoard(BoardEntity boardEntity) throws Exception;
    public HashMap<String, Object> insertBoard(BoardEntity boardEntity) throws Exception;
    public HashMap<String, Object> updateBoard(BoardEntity boardEntity) throws Exception;
    public HashMap<String, Object> deleteBoard(BoardEntity boardEntity) throws Exception;

}
