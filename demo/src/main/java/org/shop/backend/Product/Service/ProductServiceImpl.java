package org.shop.backend.Product.Service;
import org.apache.ibatis.session.SqlSession;
import org.shop.backend.Product.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/*************************************************************
 /* SYSTEM NAME      : Service
 /* PROGRAM NAME     : ProductServiceImpl.class
 /* DESCRIPTION      :
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.03.24   KIMDONGMIN   INTIAL RELEASE
 /*************************************************************/

@Service("ProductService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> itemsList() throws Exception {
        return productMapper.itemsList();
        }
}
