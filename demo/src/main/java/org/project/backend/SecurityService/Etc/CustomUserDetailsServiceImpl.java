package org.project.backend.SecurityService.Etc;

import org.project.backend.SecurityService.Model.MemberEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/*************************************************************
 /* SYSTEM NAME      : SecurityService/Etc
 /* PROGRAM NAME     : CustomUserDetailsServiceImpl.java
 /* DESCRIPTION      :
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.03.24   KIMDONGMIN   INTIAL RELEASE
 /*2025.03.31   KIMDONGMIN   흐름예시
 /*2025.04.14   KIMDONGMIN   소스 코드 수정
 /*************************************************************/

public class CustomUserDetailsServiceImpl implements UserDetails {

    private final MemberEntity memberEntity;

    public CustomUserDetailsServiceImpl(MemberEntity memberEntity) {
        this.memberEntity = memberEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {
                //return memberEntity.get("role").toString();
                System.out.println("getRole :::" + memberEntity.getRole());
                return memberEntity.getRole();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return memberEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return memberEntity.getUsername();
    }


    public String getId() { return memberEntity.getId(); }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }
}