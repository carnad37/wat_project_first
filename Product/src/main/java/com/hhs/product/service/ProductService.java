package com.hhs.product.service;

import com.hhs.product.dao.ProductMapper;
import com.hhs.product.entity.ProductEntity;
import com.hhs.product.entity.ProductSearchEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
        if (searchEntity == null) {
            searchEntity = new ProductSearchEntity();
        } else {
            int page = searchEntity.getPage();
            if (page > 0) {
                //쿼리에선 0부터 시작.
                page--;

                page = page * searchEntity.getPageViewCnt();
                searchEntity.setPage(page);
            } else {
                //페이징을 하지않는다.
                searchEntity.setPageViewCnt(0);
            }
        }
        return productMapper.select(searchEntity);
    }

    public List<ProductEntity> selectByIdList(List<Integer> productIdList) {
        if (CollectionUtils.isEmpty(productIdList)) {
            //TODO :: 요청 오류 아니면 @NotNull 처리
        }

        ProductSearchEntity searchEntity = new ProductSearchEntity();
        searchEntity.setProductIdList(productIdList);
        return productMapper.select(searchEntity);
    }

}
