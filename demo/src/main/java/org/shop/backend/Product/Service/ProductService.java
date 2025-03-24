package org.shop.backend.Product.Service;
import org.shop.backend.Product.Model.Product;

import java.util.HashMap;

/*************************************************************
 /* SYSTEM NAME      : Service
 /* PROGRAM NAME     : MemberService.interface
 /* DESCRIPTION      :
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.03.24   KIMDONGMIN   INTIAL RELEASE
 /*************************************************************/

public interface ProductService {
    public HashMap<String, Object> userInfo(Product product) throws Exception;
}
