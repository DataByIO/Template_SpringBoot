package org.project.backend.SecurityService.Etc;

import org.project.backend.SecurityService.Model.MemberEntity;
import org.project.backend.SecurityService.Service.JoinService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*************************************************************
 /* SYSTEM NAME      : SecurityService/Etc
 /* PROGRAM NAME     : CustomUserDetailsService.java
 /* DESCRIPTION      :
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.03.24   KIMDONGMIN   INTIAL RELEASE
 /*2025.03.31   KIMDONGMIN   흐름예시
 /*2025.04.14   KIMDONGMIN   소스 코드 수정
 /*************************************************************/

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final JoinService joinService;

    public CustomUserDetailsService(JoinService joinService) {
        this.joinService = joinService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //DB에서 조회
        MemberEntity userData = joinService.findByUsername(username);
        return new CustomUserDetailsServiceImpl(userData);

    }
}