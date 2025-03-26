package org.shop.backend.SecurityTest.Repository;
import org.shop.backend.SecurityTest.Entity.MemberEntity;

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
}
