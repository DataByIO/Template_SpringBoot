package org.project.backend.SecurityService.Service;

import org.project.backend.SecurityService.Model.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service("MemberService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public HashMap<String, Object> userInfo(MemberEntity memberEntity) throws Exception {
        return memberMapper.userInfo(memberEntity);
    }
}
