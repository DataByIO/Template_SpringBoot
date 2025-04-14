package org.project.backend.SecurityService.Service;

import org.project.backend.SecurityService.Model.MemberEntity;

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
    public HashMap<String, Object> userInfo(MemberEntity memberEntity) throws Exception;
}