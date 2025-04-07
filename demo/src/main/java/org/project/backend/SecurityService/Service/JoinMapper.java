package org.project.backend.SecurityService.Service;


import org.apache.ibatis.annotations.Mapper;
import org.project.backend.Member.Model.Member;
import org.project.backend.SecurityService.Model.MemberEntity;

import java.util.HashMap;

@Mapper
public interface JoinMapper {
    public HashMap<String, Object> memberInfo(String memberEntity) throws Exception;
    public void insertMember(MemberEntity memberEntity) throws Exception;
    public MemberEntity findByUsername(String memberEntity);
}