package org.project.backend.SecurityService.Service;
import org.project.backend.SecurityService.Model.RefreshEntity;

/*************************************************************
 /* SYSTEM NAME      : Service
 /* PROGRAM NAME     : MemberService.interface
 /* DESCRIPTION      :
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.03.24   KIMDONGMIN   INTIAL RELEASE
 /*************************************************************/

public interface RefreshService {
    public void insertByRefresh(RefreshEntity refreshEntity);
    public void deleteByRefresh(String refresh);
    public Boolean existsByRefresh(String refresh);

}
