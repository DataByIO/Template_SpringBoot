package org.project.backend.Board.Service;

import org.project.backend.Board.Model.BoardEntity;
import org.project.backend.Product.Service.ProductMapper;
import org.project.backend.SecurityService.Model.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/*************************************************************
 /* SYSTEM NAME      : Service
 /* PROGRAM NAME     : BoardServiceImpl.class
 /* DESCRIPTION      :
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.04.14   KIMDONGMIN   INTIAL RELEASE
 /*************************************************************/

@Service("BoardService")
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardMapper BoardMapper;

    @Override
    public HashMap<String, Object> selectBoard(BoardEntity boardEntity) throws Exception {
        //
        return BoardMapper.selectBoard(boardEntity);
    }

    @Override
    public HashMap<String, Object> insertBoard(BoardEntity boardEntity) throws Exception {
        return null;
    }

    @Override
    public HashMap<String, Object> updateBoard(BoardEntity boardEntity) throws Exception {
        return null;
    }

    @Override
    public HashMap<String, Object> deleteBoard(BoardEntity boardEntity) throws Exception {
        return null;
    }
}
