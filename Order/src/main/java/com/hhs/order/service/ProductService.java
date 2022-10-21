package com.hhs.order.service;

import com.hhs.order.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * 제품 관련 통신이 이행되는 서비스
 * DB가 join에 쓸 데이터를 가져오는 느낌.
 *
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    @Qualifier("productTemplate")
    private final RestTemplate productTemplate;


    public ProductEntity select(ProductEntity product) {

        return null;
    }

}
