package com.hhs.order;

import com.hhs.order.entity.ProductEntity;
import com.hhs.order.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OrderServiceTests {

    @Autowired
    private ProductService productService;

    /**
     * 제품값 체크
     */
    @Test
    public void productChecker() {

        List<ProductEntity> productEntityList = new ArrayList<>();

        ProductEntity product1 = new ProductEntity();
        product1.setProductId(1);

        ProductEntity product2 = new ProductEntity();
        product2.setProductId(2);

        productEntityList.add(product1);
        productEntityList.add(product2);

        Map<Integer, ProductEntity> resultMap = productService.select(productEntityList);

        assertNotNull(resultMap);

    }


}
