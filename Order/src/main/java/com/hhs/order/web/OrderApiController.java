package com.hhs.order.web;

import com.hhs.order.entity.OrderEntity;
import com.hhs.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1.0")
public class OrderApiController {

    private final OrderService orderService;

    /**
     * 상품 등록
     * @return
     */
    @PostMapping (value = "create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> orderCreate(@RequestBody OrderEntity orderEntity) {
        int retCnt = orderService.insert(orderEntity);

        return null;
    }

    /**
     * 상품 상세
     * @return
     */
    @GetMapping(value = "read", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> productRead() {


        return null;
    }

    /**
     * 상품 전체
     * @return
     */
    @GetMapping(value = "all_read", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> productAllRead() {


        return null;
    }

    /**
     * 상품 등정록
     * @return
     */
    @GetMapping(value = "read/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> productPageRead() {


        return null;
    }

    /**
     * 상품 수정
     * @return
     */
    @GetMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> productUpdate() {


        return null;
    }

    /**
     * 상품 삭제
     * @return
     */
    @GetMapping(value = "delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> productDelete() {


        return null;
    }

}
