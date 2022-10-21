package com.hhs.product.dao;

import com.hhs.product.entity.ProductEntity;
import com.hhs.product.entity.ProductSearchEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<ProductEntity> select(ProductSearchEntity searchEntity);

    int insert(ProductEntity product);

    int update(ProductEntity product);

    int delete(ProductEntity product);

}
