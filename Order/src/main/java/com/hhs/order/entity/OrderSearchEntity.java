package com.hhs.order.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OrderSearchEntity {

    private static final int DEFAULT_PAGE_VIEW_CNT = 10;

    //유저 Id
    private String userId;

    //주문 Id
    private int orderId;

    //제품 Id
    private int productId;

    //검색할 페이지
    private int page;

    //페이지에 노출될 데이터
    private int pageViewCnt = DEFAULT_PAGE_VIEW_CNT;

    //검색어
    private String searchWord;

    //검색 시작
    private LocalDate searchStartDate;

    //검색 종료
    private LocalDate searchEndDate;

}
