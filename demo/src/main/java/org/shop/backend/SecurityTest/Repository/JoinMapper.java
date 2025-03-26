package org.shop.backend.SecurityTest.Repository;


import org.apache.ibatis.annotations.Mapper;
import org.shop.backend.SecurityTest.Entity.MemberEntity;

import java.util.HashMap;

@Mapper
public interface JoinMapper {
    public HashMap<String, Object> memberInfo(String memberEntity) throws Exception;
    public void insertMember(MemberEntity memberEntity) throws Exception;
    public HashMap<String, Object> findByUsername(String memberEntity);
}
