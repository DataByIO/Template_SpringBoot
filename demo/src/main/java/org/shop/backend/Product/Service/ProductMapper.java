package org.shop.backend.Product.Service;


import org.apache.ibatis.annotations.Mapper;
import org.shop.backend.Member.Model.Member;

import java.util.HashMap;

@Mapper
public interface ProductMapper {

    public HashMap<String, Object> userInfo(Member member);

}
