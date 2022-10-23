package com.hhs.order.service;

import com.hhs.order.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
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


    public Map<Integer, ProductEntity> select(List<ProductEntity> productList) {

        ProductEntity[] result;
        List<Integer> productIdList= productList.stream().map(ProductEntity::getProductId).collect(Collectors.toList());

        //body 구성
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("productIdList", productIdList);

        //요청전송
        //TODO :: 연결실패 exception 처리
        ResponseEntity<ProductEntity[]> resultEntity = productTemplate.postForEntity("http://localhost:8080/product/api/select/id/multi", paramMap, ProductEntity[].class);
        result = resultEntity.getBody();
        //TODO :: result가 비어있을경우 처리

        return Arrays.stream(result).collect(Collectors.toMap(ProductEntity::getProductId, Function.identity()));

    }

}
