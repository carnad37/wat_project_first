package com.hhs.product.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDate;

@Data
@Alias("ProductSearchEntity")
public class ProductSearchEntity {

    //주문 Id
    private long productId;

    //제품명
    private String name;

    //검색할 페이지
    private int page;

    //페이지에 노출될 데이터
    private int pageViewCnt = 10;

    //검색 시작
    private LocalDate searchStartDate;

    //검색 종료
    private LocalDate searchEndDate;

}
