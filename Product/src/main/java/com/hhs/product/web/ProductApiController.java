package com.hhs.product.web;

import com.hhs.product.entity.ProductEntity;
import com.hhs.product.entity.ProductResponseEntity;
import com.hhs.product.entity.ProductSearchEntity;
import com.hhs.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductApiController {

    private final ProductService productService;

    @GetMapping(value = "select", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponseEntity<List<ProductEntity>>> select(ProductSearchEntity searchEntity) {
        List<ProductEntity> result = productService.select(searchEntity);

        ProductResponseEntity response = new ProductResponseEntity();
        response.setMessage("success");
        response.setResult(result);

        return ResponseEntity.ok(response);
    }

    /**
     * 통신용 api
     * @param productIdList
     * @return
     */
    @GetMapping(value = "select/id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductEntity>> selectByIdList(@RequestBody @RequestParam(name = "productIdList") List<Integer> productIdList) {
        List<ProductEntity> result = productService.selectByIdList(productIdList);

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
