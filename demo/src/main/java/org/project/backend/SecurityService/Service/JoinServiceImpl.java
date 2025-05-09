package org.project.backend.SecurityService.Service;

import org.project.backend.SecurityService.Model.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/*************************************************************
 /* SYSTEM NAME      : Service
 /* PROGRAM NAME     : MemberServiceImpl.class
 /* DESCRIPTION      :
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.03.24   KIMDONGMIN   INTIAL RELEASE
 /*************************************************************/

@Service("JoinService")
public class JoinServiceImpl implements JoinService {

    @Autowired
    private JoinMapper joinMapper;

    @Override
    public HashMap<String, Object> memberInfo(MemberEntity memberEntity) throws Exception {
        return joinMapper.memberInfo(String.valueOf(memberEntity));
    }

    @Override
    public void insertMember(MemberEntity memberEntity) throws Exception {
        joinMapper.insertMember(memberEntity);
    }

    @Override
    public MemberEntity findByUsername(String username) {
        return joinMapper.findByUsername(username);
    }
}
