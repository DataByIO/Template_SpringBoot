package org.project.backend.Board.Service;

import org.project.backend.SecurityService.Model.MemberEntity;

import java.util.HashMap;

public interface BoardService {
    public HashMap<String, Object> selectBoard(MemberEntity memberEntity) throws Exception;
    public HashMap<String, Object> insertBoard(MemberEntity memberEntity) throws Exception;
    public HashMap<String, Object> updateBoard(MemberEntity memberEntity) throws Exception;
    public HashMap<String, Object> deleteBoard(MemberEntity memberEntity) throws Exception;

}
