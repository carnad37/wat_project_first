package com.hhs.product.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDate;
import java.util.List;

@Data
@Alias("productSearchEntity")
public class ProductSearchEntity {

    public static final int DEFAULT_PAGE_VIEW_CNT = 10;

    //주문 Id
    private int productId;

    //제품명
    private String name;

    //검색할 페이지
    private int page;

    //페이지에 노출될 데이터
    private int pageViewCnt = DEFAULT_PAGE_VIEW_CNT;

    //검색 시작
    private LocalDate searchStartDate;

    //검색 종료
    private LocalDate searchEndDate;

    //검색용 아이디
    private List<Integer> productIdList;

}
