package com.hhs.order.web;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1.0")
public class OrderApiController {

    /**
     * 상품 등록
     * @return
     */
    @GetMapping(value = "create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> productCreate() {


        return null;
    }

    /**
     * 상품 등록
     * @return
     */
    @GetMapping(value = "read", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> productRead() {


        return null;
    }

    /**
     * 상품 등록
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
     * 상품 수
     * @return
     */
    @GetMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> productUpdate() {


        return null;
    }

    /**
     * 상품 삭
     * @return
     */
    @GetMapping(value = "delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> productDelete() {


        return null;
    }

}
