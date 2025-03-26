package org.shop.backend.SecurityTest.Repository;

import lombok.SneakyThrows;
import org.shop.backend.SecurityTest.Entity.MemberEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final JoinService joinService;

    public CustomUserDetailsService(JoinService joinService) {
        this.joinService = joinService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //DB에서 조회
        HashMap<String, Object> userData = joinService.findByUsername(username);
        return new CustomUserDetails(userData);

    }
}
