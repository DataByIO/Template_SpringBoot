package org.shop.backend.SecurityService.Service;
import jakarta.transaction.Transactional;
import org.shop.backend.SecurityService.Model.MemberEntity;
import org.shop.backend.SecurityService.Model.RefreshEntity;
import org.springframework.data.jpa.repository.JpaRepository;

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

public interface RefreshService {
    public void insertByRefresh(RefreshEntity refreshEntity);
    public void deleteByRefresh(String refresh);
    public Boolean existsByRefresh(String refresh);

}
