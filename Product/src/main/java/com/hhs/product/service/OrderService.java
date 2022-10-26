package com.hhs.product.service;

import com.hhs.product.entity.ProductResponseEntity;
import com.hhs.product.except.ServiceMessageException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    @Qualifier("orderTemplate")
    private final RestTemplate orderTemplate;

    public boolean checkNotExistOrderByProductId(int productId) {

        Map<String, Integer> param = new HashMap<>();
        param.put("productId", productId);

        ResponseEntity<ProductResponseEntity> responseEntity;
        try {
            responseEntity = orderTemplate.postForEntity("http://localhost:9090/order/api/exist/product/id", param, ProductResponseEntity.class);
        } catch (RestClientException re) {
            log.warn(re.getMessage());
            throw new ServiceMessageException("통신에 실패하였습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServiceMessageException("알수 없는 오류가 발생하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        ProductResponseEntity<Boolean> response = responseEntity.getBody();

        return response.getResult() != null && response.getResult();

    }

}
