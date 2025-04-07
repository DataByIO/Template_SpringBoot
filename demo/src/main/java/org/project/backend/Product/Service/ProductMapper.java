package org.project.backend.Product.Service;


import org.apache.ibatis.annotations.Mapper;
import org.project.backend.Product.Model.Product;

import java.util.List;

@Mapper
public interface ProductMapper {

    public List<Product> itemsList() throws Exception;

}
