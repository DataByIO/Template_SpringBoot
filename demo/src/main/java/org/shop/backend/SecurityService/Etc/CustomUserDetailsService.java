package org.shop.backend.SecurityService.Etc;

import org.shop.backend.SecurityService.Model.MemberEntity;
import org.shop.backend.SecurityService.Service.JoinService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
