package org.project.backend.Product.Controller;

import org.project.backend.Product.Model.Product;
import org.project.backend.Product.Service.ProductService;
import org.project.backend.Product.Service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private ProductServiceImpl productServiceImpl;

    @GetMapping("/api/items")
    public ResponseEntity getItems() throws Exception {
        List<Product> items = productServiceImpl.itemsList();
        return ResponseEntity.ok(items);
    }
}
