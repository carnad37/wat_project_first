package com.hhs.order.service;

import com.hhs.order.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public Map<Integer, ProductEntity> select(List<ProductEntity> productIdList) {

        ProductEntity[] result;
        String param = productIdList.stream().map(x->String.valueOf(x.getProductId())).collect(Collectors.joining(","));
        ResponseEntity<ProductEntity[]> resultEntity = productTemplate.getForEntity("http://localhost:8080/product/api/select/id?productIdList=" + param, ProductEntity[].class);
        result = resultEntity.getBody();

        return Arrays.stream(result).collect(Collectors.toMap(ProductEntity::getProductId, Function.identity()));

    }

}
