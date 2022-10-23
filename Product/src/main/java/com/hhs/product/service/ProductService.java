package com.hhs.product.service;

import com.hhs.product.dao.ProductMapper;
import com.hhs.product.entity.ProductEntity;
import com.hhs.product.entity.ProductSearchEntity;
import com.hhs.product.except.ServiceMessageException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductMapper productMapper;

    private final OrderService orderService;

    public ProductEntity insert(ProductEntity product) throws ServiceMessageException {
        product.setCreateTime(LocalDateTime.now());
        //TODO :: DB 관련된 exception 처리필요.
        int insCnt = productMapper.insert(product);
        if (insCnt < 1) {
            throw new ServiceMessageException("제품 등록에 실패하였습니다.");
        }
        return product;
    }

    @Transactional
    public ProductEntity update(ProductEntity product) throws ServiceMessageException {
        //제품 수정시 현재 주문중인 제품이 없어야한다.
        if (!orderService.checkNotExistOrderByProductId(product.getProductId())) {
            throw new ServiceMessageException("해당 제품은 주문중입니다");
        }

        product.setUpdateTime(LocalDateTime.now());
        //TODO :: DB 관련된 exception 처리필요.
        int updCnt = productMapper.update(product);

        if (updCnt > 0) {
            ProductSearchEntity searchEntity = new ProductSearchEntity();
            searchEntity.setProductId(product.getProductId());
            product = productMapper.select(searchEntity).get(0);    //무조건 한개이상 리턴됨.
        } else {
            throw new ServiceMessageException("제품 수정에 실패하였습니다");
        }
        return product;
    }

    public void delete(ProductEntity product) throws ServiceMessageException {
        //제품 삭제시 현재 진행중인 주문이 없어야만 한다.
        if (!orderService.checkNotExistOrderByProductId(product.getProductId())) {
            throw new ServiceMessageException("해당 제품은 주문중입니다");
        }

        product.setDeleteTime(LocalDateTime.now());
        //TODO :: DB 관련된 exception 처리필요.
        int delCnt = productMapper.delete(product);

        if (delCnt < 1) {
            throw new ServiceMessageException("제품 삭제에 실패했습니다");
        }

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
        //TODO :: DB 관련된 exception 처리필요.
        return productMapper.select(searchEntity);
    }

    public List<ProductEntity> selectByIdList(List<Integer> productIdList) {
        if (CollectionUtils.isEmpty(productIdList)) {
            //TODO :: 요청 오류 아니면 @NotNull 처리
        }

        ProductSearchEntity searchEntity = new ProductSearchEntity();
        searchEntity.setProductIdList(productIdList);
        //TODO :: DB 관련된 exception 처리필요.
        return productMapper.select(searchEntity);
    }

}
