package com.hhs.order.entity;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 상식상에서 필요한 주문정보
 *
 * 스키마는 다음과 같다.
 *
 * 유저 id  NotNull 유저가 없으므로 임의로 입력받게해야함
 * 주문 id  NotNull 주문시 id 발급.
 * 제품 id  NotNull Product MSA에서 값을 가져온다.
 * 수량     NotNull 구매숫
 * 주문 시간 NotNull 주문한 시간.
 * 업뎃 시간 Null    주문 내용이 수정된 시간.
 * 취소 시간 Null    주문 취소. 취소값이 없으면 주문상태. 있으면 취소상태.
 *
 */
@Getter
@Setter
public class OrderEntity {

    //주문 아이디
    private int orderId;

    //유저 아이디
    private String userId;

    //제품 번호 리스트
    private List<Integer> productIdList;

    //제품 리스트
    private List<ProductEntity> productList;

    //등록시간
    private LocalDateTime createTime;

    //삭제시간
    private LocalDateTime deleteTime;

}
