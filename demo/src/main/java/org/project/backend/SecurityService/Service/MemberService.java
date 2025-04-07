package org.project.backend.SecurityService.Service;

import org.project.backend.Member.Model.Member;
import org.project.backend.SecurityService.Model.MemberEntity;

import java.util.HashMap;

public interface MemberService {
    public HashMap<String, Object> userInfo(MemberEntity memberEntity) throws Exception;
}