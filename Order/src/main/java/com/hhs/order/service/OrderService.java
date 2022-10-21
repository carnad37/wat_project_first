package com.hhs.order.service;

import com.hhs.order.entity.OrderEntity;
import com.hhs.order.entity.OrderSearchEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

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
     * @param productIdList
     * @return
     */
    public int insert(String userId, List<Integer> productIdList) {
        //주문이 등록된 횟수
        int result = 0;
        for (Integer productId: productIdList) {
            //null 제거
            if (productId == null) continue;
            //등록될 주문 객체
            result++;
            new OrderEntity(productId, userId, LocalDateTime.now());
        }
        return result;
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
        int page = searchEntity.getPage();
        if (page > 0) {
            int pageViewCnt = searchEntity.getPageViewCnt();
            searchEntity.setPageViewCnt((page - 1) * pageViewCnt);
        }

        

        return new ArrayList<>();
    }

    /**
     * 주문 수정
     *
     * 기본적으로 유저 id와 주문번호를 통해 이루워진다.
     * 전달된 제품들을 비교하여
     * 기존값들을 변경하여 같은 주문번호로 재저장.
     *
     * 또는
     *
     * 전부 삭제후에 새로등록.
     *
     * @return
     */
    public int update(String userId, long orderId, List<Integer> productIdList) {

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
