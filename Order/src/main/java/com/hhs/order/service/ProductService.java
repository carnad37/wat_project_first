package com.hhs.order.service;

import com.hhs.order.entity.ProductEntity;
import com.hhs.order.except.ServiceMessageException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
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
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    @Qualifier("productTemplate")
    private final RestTemplate productTemplate;


    public Map<Integer, ProductEntity> select(List<ProductEntity> productList) throws ServiceMessageException {

        ProductEntity[] result;
        List<Integer> productIdList= productList.stream().map(ProductEntity::getProductId).collect(Collectors.toList());

        //body 구성
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("productIdList", productIdList);

        //요청전송
        ResponseEntity<ProductEntity[]> resultEntity;
        try {
            resultEntity = productTemplate.postForEntity("http://localhost:8080/product/api/select/id/multi", paramMap, ProductEntity[].class);
        } catch (RestClientException re) {
            log.warn(re.getMessage());
            throw new ServiceMessageException("제품 정보 확인에 실패하였습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServiceMessageException("알수 없는 오류가 발생하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        result = resultEntity.getBody();

        return Arrays.stream(result).collect(Collectors.toMap(ProductEntity::getProductId, Function.identity()));

    }

}
