package org.shop.backend.Member.Service;


import org.shop.backend.Member.Model.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface MemberMapper {

    public HashMap<String, Object> userInfo(Member member);

}
