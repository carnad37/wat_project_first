package com.hhs.product;

import com.hhs.product.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProductServiceTests {

    @Autowired
    private OrderService orderService;

    /**
     * 제품 수정가능 체크
     */
    @Test
    public void productChecker() {

        boolean test = orderService.checkNotExistOrderByProductId(4);

        assertTrue(test);

    }


}
