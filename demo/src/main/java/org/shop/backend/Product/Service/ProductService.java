package org.shop.backend.Product.Service;
import org.shop.backend.Product.Model.Product;
import java.util.HashMap;
import java.util.List;

/*************************************************************
 /* SYSTEM NAME      : Service
 /* PROGRAM NAME     : ProductService.interface
 /* DESCRIPTION      :
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.03.24   KIMDONGMIN   INTIAL RELEASE
 /*************************************************************/

public interface ProductService {
    public List<Product> itemsList() throws Exception;
}
