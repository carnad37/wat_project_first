package com.hhs.order.service;

import com.hhs.order.dao.OrderMapper;
import com.hhs.order.entity.OrderEntity;
import com.hhs.order.entity.OrderSearchEntity;
import com.hhs.order.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductService productService;

    private final OrderMapper orderMapper;

    /**
     * 필요한 기능은 다음과 같다.
     * 등록
     * 상세조회
     * 리스트 전체조회
     * 주문 리스트 페이징 조회
     * 주문 수정
     * 주문 삭제
     * 구체적인 주문과정?
     *
     * 주문은 한번에 여러개 이루워질수있음
     * 각각의 주문들
     *
     */

    /**
     * 주문 등록
     * 제품 id를 통해 여러개 동시 등록도 가능해야한다.
     *
     * 주문자 정보
     * 주문제품 아이디 리스트
     * @param orderEntity
     * @return
     */
    public int insert(OrderEntity orderEntity) {
        //주문번호 리스트체크후 제품정보 전송받기
        if (!CollectionUtils.isEmpty(orderEntity.getProductList())) {
            Map<Integer, ProductEntity> productMap = productService.select(orderEntity.getProductList());
            List<ProductEntity> productList = new ArrayList<>();
            for (ProductEntity tProduct: orderEntity.getProductList()) {
                ProductEntity rProduct = productMap.getOrDefault(tProduct.getProductId(), null);
                if (rProduct != null) {
                    tProduct.setTotal(rProduct.getPrice() * tProduct.getAmount());
                    productList.add(tProduct);
                }
            }
            //값이 정리된 상품들 정보 담기
            orderEntity.setProductList(productList);
            orderEntity.setCreateTime(LocalDateTime.now());
        }

        return orderMapper.insert(orderEntity);
    }

    /**
     * 주문 검색의 구성은 다음과 같다.
     *
     * 구매자 조건 (필수)
     * 주문일 범위 검색 (between startDate and endDate)
     * 제품명 검색 (Like 검색)
     * 주문번호 검색 (=orderId)
     *
     * ---- 보류 ----
     *
     * 제품 상태 검색(구매, 구매취소)
     */
    public List<OrderEntity> select(OrderSearchEntity searchEntity) {
        //페이징 체크
        //TODO :: exception 처리 필요 (페이지 < 0 이나 pageViewCnt가 OrderSearchEntity.DEFAULT_PAGE_VIEW_CNT보다 작을경우)
        if (searchEntity == null) {
            //TODO :: 서비스 호출 파라미터 오류
        } else if (!StringUtils.hasText(searchEntity.getUserId())) {
            //TODO :: 유저 아이디가 없을경우 오류
        } else {
            int page = searchEntity.getPage();
            if (page > 0) {
                //쿼리에선 0부터 시작.
                page--;

                page = page * searchEntity.getPageViewCnt();
                searchEntity.setPage(page);
            } else {
                //페이징을 하지않는다.
                searchEntity.setPageViewCnt(0);
            }
        }
        return orderMapper.select(searchEntity);
    }

    /**
     * 주문 수정
     *
     * 기본적으로 유저 id와 주문번호를 통해 이루워진다.
     * 기존의 주문은 모두취소(delete_time을 업데이트).
     * 새로운 요청을 같은 주문번호로 등록한다.
     *
     * 또는
     *
     * 전부 삭제후에 새로등록.
     *
     * @return
     */
    public int update(OrderEntity orderEntity) {
        orderMapper.delete(orderEntity);
        insert(orderEntity);
        return 0;
    }

    /**
     * 제품의 삭제
     *
     * 기본적으로 유저 id와 주문번호를 통해 이루워진다.
     *
     * @return
     */
    public int delete(String userId, long orderId) {

        return 0;
    }

}
