package org.shop.backend.SecurityService.Service;
import org.shop.backend.SecurityService.Model.MemberEntity;

import java.util.HashMap;

/*************************************************************
 /* SYSTEM NAME      : Service
 /* PROGRAM NAME     : MemberService.interface
 /* DESCRIPTION      :
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.03.24   KIMDONGMIN   INTIAL RELEASE
 /*************************************************************/

public interface JoinService {
    public HashMap<String, Object> memberInfo(MemberEntity memberEntity) throws Exception;
    public void insertMember(MemberEntity memberEntity) throws Exception;
    public MemberEntity findByUsername(String username) ;
}
