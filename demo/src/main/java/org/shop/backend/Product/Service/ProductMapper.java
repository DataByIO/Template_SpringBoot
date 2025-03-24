package org.shop.backend.Product.Service;


import org.apache.ibatis.annotations.Mapper;
import org.shop.backend.Product.Model.Product;

import java.util.List;

@Mapper
public interface ProductMapper {

    public List<Product> itemsList() throws Exception;

}
