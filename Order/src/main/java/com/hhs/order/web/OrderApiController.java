package com.hhs.order.web;

import com.hhs.order.entity.OrderEntity;
import com.hhs.order.entity.OrderResponseEntity;
import com.hhs.order.entity.OrderSearchEntity;
import com.hhs.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/")
public class OrderApiController {

    private final OrderService orderService;

    /**
     * 상품 등록
     *
     * user_id
     * productList
     *
     * @return
     */
    @PostMapping (value = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponseEntity> orderCreate(@RequestBody OrderEntity orderEntity) {
        //필요한값만 넘김.
        OrderEntity sendEntity = new OrderEntity();
        sendEntity.setUserId(orderEntity.getUserId());
        sendEntity.setProductList(orderEntity.getProductList());

        OrderEntity orderResult = orderService.insert(sendEntity);

        OrderResponseEntity<OrderEntity> response = new OrderResponseEntity<>();
        response.setMessage("주문이 완료되었습니다");
        response.setResult(orderResult);

        return ResponseEntity.ok(response);
    }

    /**
     * 주문 검색
     *
     * user_id
     * searchStartDate
     * searchEndDate
     *
     * @param searchEntity
     * @return
     */
    @PostMapping(value = "select", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponseEntity> productRead(@RequestBody OrderSearchEntity searchEntity) {
        OrderSearchEntity sendEntity = new OrderSearchEntity();
        sendEntity.setUserId(searchEntity.getUserId());
        sendEntity.setOrderId(searchEntity.getOrderId());
        sendEntity.setSearchStartDate(searchEntity.getSearchStartDate());
        sendEntity.setSearchEndDate(searchEntity.getSearchEndDate());

        List<OrderEntity> orderEntity = orderService.select(sendEntity);

        OrderResponseEntity<List<OrderEntity>> response = new OrderResponseEntity<>();
        response.setMessage("조회에 성공하였습니다.");
        response.setResult(orderEntity);

        return ResponseEntity.ok(response);
    }

    /**
     * 주문 상세 검색
     *
     * user_id
     * order_id
     *
     * @param searchEntity
     * @return
     */
    @PostMapping(value = "select/detail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponseEntity> productReadDetail(@RequestBody OrderSearchEntity searchEntity) {
        //필요한값만 넘김.
        OrderSearchEntity sendEntity = new OrderSearchEntity();
        sendEntity.setUserId(searchEntity.getUserId());
        sendEntity.setOrderId(searchEntity.getOrderId());

        OrderEntity orderResult = orderService.selectDetail(sendEntity);

        OrderResponseEntity<OrderEntity> response = new OrderResponseEntity<>();
        //주문 상세내역 체크
        if (CollectionUtils.isEmpty(orderResult.getProductList())) {
            response.setMessage("등록된 주문이 없습니다.");
            response.setResult(null);
        } else {
            response.setMessage("조회에 성공하였습니다.");
            response.setResult(orderResult);
        }

        return ResponseEntity.ok(response);
    }

    /**
     * 특정 제품의 변경이 가능한지 확인.
     * 유저체크 없음. 최소한의 정보만 전달.
     *
     * user_id
     * order_id
     *
     * @param searchEntity
     * @return
     */
    @PostMapping(value = "exist/product/id", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponseEntity> orderChangeChecker(@RequestBody OrderSearchEntity searchEntity) {
        //필요한값만 넘김.
        OrderSearchEntity sendEntity = new OrderSearchEntity();
        sendEntity.setProductId(searchEntity.getProductId());

        boolean result = orderService.checkByProductId(sendEntity);

        OrderResponseEntity<Boolean> response = new OrderResponseEntity<>();
        response.setResult(result);

        return ResponseEntity.ok(response);
    }

    /**
     * 상품 수정
     *
     * user_id
     * order_id
     * productList
     *
     * @return
     */
    @PutMapping(value = "update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> productUpdate(@RequestBody OrderEntity orderEntity) {
        //필요한값만 넘김.
        OrderEntity sendEntity = new OrderEntity();
        sendEntity.setOrderId(orderEntity.getOrderId());
        sendEntity.setUserId(orderEntity.getUserId());
        sendEntity.setProductList(orderEntity.getProductList());

        OrderEntity orderResult = orderService.update(sendEntity);

        OrderResponseEntity<OrderEntity> response = new OrderResponseEntity<>();
        response.setMessage("주문이 완료되었습니다");
        response.setResult(orderResult);

        return ResponseEntity.ok(response);
    }

    /**
     * 상품 삭제
     *
     * user_id
     * order_id
     *
     * @return
     */
    @DeleteMapping(value = "delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponseEntity> productDelete(@RequestBody OrderEntity orderEntity) {
        //필요한값만 넘김.
        OrderEntity sendEntity = new OrderEntity();
        sendEntity.setOrderId(orderEntity.getOrderId());
        sendEntity.setUserId(orderEntity.getUserId());

        OrderResponseEntity<Integer> response = new OrderResponseEntity<>();
        int delCnt = orderService.delete(sendEntity);
        if(delCnt > 0) {
            response.setMessage("성공적으로 주문이 취소되었습니다.");
        } else {
            response.setMessage("주문 삭제에 실패하였습니다.");
        }
        response.setResult(delCnt);

        return ResponseEntity.ok(response);
    }



}
