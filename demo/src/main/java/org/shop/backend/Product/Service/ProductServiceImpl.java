package org.shop.backend.Product.Service;

import org.shop.backend.Member.Service.MemberMapper;
import org.shop.backend.Product.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/*************************************************************
 /* SYSTEM NAME      : Service
 /* PROGRAM NAME     : MemberServiceImpl.class
 /* DESCRIPTION      :
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.03.24   KIMDONGMIN   INTIAL RELEASE
 /*************************************************************/

@Service("MemberService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public HashMap<String, Object> userInfo(Product product) throws Exception {
        return memberMapper.userInfo(product);
        }
}
