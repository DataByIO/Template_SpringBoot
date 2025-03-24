package org.shop.backend.Product.Controller;

import org.shop.backend.Product.Model.Product;
import org.shop.backend.Product.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*************************************************************
 /* SYSTEM NAME      : controller
 /* PROGRAM NAME     : ProductController.class
 /* DESCRIPTION      :
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.03.24   KIMDONGMIN   INTIAL RELEASE
 /*************************************************************/

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/api/items")
    public List<Product> getItems() throws Exception {
        List<Product> items = productService.itemsList();
        return items;
    }
}
