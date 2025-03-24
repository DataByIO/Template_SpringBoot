package org.shop.backend.Member.Service;
import org.shop.backend.Member.Model.Member;
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

public interface MemberService {
    public HashMap<String, Object> userInfo(Member member) throws Exception;
}
