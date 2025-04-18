package org.project.backend.Board.Service;

import org.apache.ibatis.annotations.Mapper;
import org.project.backend.Board.Model.BoardEntity;

import java.util.HashMap;

@Mapper
public interface BoardMapper {
    public HashMap<String, Object> selectBoard(BoardEntity boardEntity) throws Exception;
    public HashMap<String, Object> insertBoard(BoardEntity boardEntity) throws Exception;
    public HashMap<String, Object> updateBoard(BoardEntity boardEntity) throws Exception;
    public HashMap<String, Object> deleteBoard(BoardEntity boardEntity) throws Exception;
}
