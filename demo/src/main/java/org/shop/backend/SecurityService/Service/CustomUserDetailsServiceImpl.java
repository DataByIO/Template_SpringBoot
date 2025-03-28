package org.shop.backend.SecurityService.Service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class CustomUserDetailsServiceImpl implements UserDetails {

    private final HashMap<String, Object> memberEntity;

    public CustomUserDetailsServiceImpl(HashMap<String, Object> memberEntity) {
        this.memberEntity = memberEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {

                return memberEntity.get("role").toString();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return memberEntity.get("password").toString();
    }

    @Override
    public String getUsername() {
        return memberEntity.get("username").toString();
    }

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
