package org.project.backend.SecurityService.Service;

import org.apache.ibatis.annotations.Mapper;
import org.project.backend.SecurityService.Model.MemberEntity;

import java.util.HashMap;

@Mapper
public interface MemberMapper {
    public HashMap<String, Object> userInfo(MemberEntity memberEntity) throws Exception;

}