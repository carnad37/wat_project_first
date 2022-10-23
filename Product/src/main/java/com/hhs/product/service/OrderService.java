package com.hhs.product.service;

import com.hhs.product.entity.ProductResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Qualifier("orderTemplate")
    private final RestTemplate orderTemplate;

    public boolean checkNotExistOrderByProductId(int productId) {

        Map<String, Integer> param = new HashMap<>();
        param.put("productId", productId);

        ResponseEntity<ProductResponseEntity> responseEntity = orderTemplate.postForEntity("http://localhost:9090/order/api/exist/product/id", param, ProductResponseEntity.class);
        ProductResponseEntity<Boolean> response = responseEntity.getBody();

        return response.getResult() != null && response.getResult();

    }

}
