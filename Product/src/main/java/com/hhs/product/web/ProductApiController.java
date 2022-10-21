package com.hhs.product.web;

import com.hhs.product.entity.ProductEntity;
import com.hhs.product.entity.ProductSearchEntity;
import com.hhs.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductApiController {

    private final ProductService productService;

    @GetMapping(value = "select", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductEntity>> select(ProductSearchEntity searchEntity) {
        List<ProductEntity> result = productService.select(searchEntity);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(ProductEntity product) {
        //유효성체크
        productService.insert(product);
        return ResponseEntity.ok("success");
    }

    @GetMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductEntity> update(ProductEntity product) {
        //유효성체크
        productService.update(product);
        return ResponseEntity.ok(product);
    }

    @GetMapping(value = "delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public String delete(ProductEntity product) {
        productService.delete(product);
        return null;
    }


}
