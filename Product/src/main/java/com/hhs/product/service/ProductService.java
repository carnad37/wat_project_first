package com.hhs.product.service;

import com.hhs.product.dao.ProductMapper;
import com.hhs.product.entity.ProductEntity;
import com.hhs.product.entity.ProductSearchEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    public int insert(ProductEntity product) {
        product.setCreateTime(LocalDateTime.now());
        return productMapper.insert(product);
    }

    public int update(ProductEntity product) {
        product.setUpdateTime(LocalDateTime.now());
        return productMapper.update(product);
    }

    public int delete(ProductEntity product) {
        product.setDeleteTime(LocalDateTime.now());
        return productMapper.delete(product);
    }

    public List<ProductEntity> select(ProductSearchEntity searchEntity) {
        searchEntity = searchEntity == null ? new ProductSearchEntity() :  searchEntity;
        return productMapper.select(searchEntity);
    }

}
