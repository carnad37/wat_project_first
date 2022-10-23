package com.hhs.product.web;

import com.hhs.product.entity.ProductEntity;
import com.hhs.product.entity.ProductResponseEntity;
import com.hhs.product.entity.ProductSearchEntity;
import com.hhs.product.except.ServiceMessageException;
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

    /**
     * 일반검색
     * json 형태의 요청만 받음(post 처리)
     * @param searchEntity
     * @return
     */
    @PostMapping(value = "select", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponseEntity<List<ProductEntity>>> select(@RequestBody ProductSearchEntity searchEntity) {
        List<ProductEntity> result = productService.select(searchEntity);

        ProductResponseEntity response = new ProductResponseEntity();
        response.setMessage("success");
        response.setResult(result);

        return ResponseEntity.ok(response);
    }

    /**
     * 통신용 api
     * 여러개의 id로 동시검색
     * @param product
     * @return
     */
    @PostMapping(value = "select/id/multi", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductEntity>> selectByIdList(@RequestBody ProductEntity product) {
        List<ProductEntity> result = productService.selectByIdList(product.getProductIdList());
        return ResponseEntity.ok(result);
    }


    /**
     * 제품 등록
     * @param product
     * @return
     */
    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponseEntity> create(@RequestBody ProductEntity product) {
        //유효성체크
        ProductEntity sendEntity = new ProductEntity();
        sendEntity.setName(product.getName());
        sendEntity.setPrice(product.getPrice());

        ProductResponseEntity response = new ProductResponseEntity();
        try {
            ProductEntity resultEntity = productService.insert(sendEntity);
            response.setMessage("제품 등록에 성공하였습니다.");
            response.setResult(resultEntity);
        } catch (ServiceMessageException sme) {
            response.setMessage(sme.getMessage());
            response.setResult(null);
        }

        return ResponseEntity.ok(response);
    }

    /**
     * 제품 수정
     * @param product
     * @return
     */
    @PutMapping(value = "update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponseEntity> update(@RequestBody ProductEntity product) {
        //유효성체크
        ProductEntity sendEntity = new ProductEntity();
        sendEntity.setProductId(product.getProductId());
        sendEntity.setName(product.getName());
        sendEntity.setPrice(product.getPrice());

        ProductResponseEntity response = new ProductResponseEntity();
        try {
            ProductEntity resultEntity = productService.update(sendEntity);
            response.setMessage("제품 수정에 성공하였습니다.");
            response.setResult(resultEntity);
        } catch (ServiceMessageException sme) {
            response.setMessage(sme.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    /**
     * 제품 삭제
     * @param product
     * @return
     */
    @DeleteMapping(value = "delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponseEntity> delete(@RequestBody ProductEntity product) {
        //유효성체크
        ProductEntity sendEntity = new ProductEntity();
        sendEntity.setProductId(product.getProductId());

        ProductResponseEntity<Boolean> response = new ProductResponseEntity();

        try {
            productService.delete(product);
            response.setMessage("제품 삭제에 성공했습니다.");
            response.setResult(true);
        } catch (ServiceMessageException sme) {
            response.setMessage(sme.getMessage());
            response.setResult(false);
        }

        return ResponseEntity.ok(response);
    }


}
