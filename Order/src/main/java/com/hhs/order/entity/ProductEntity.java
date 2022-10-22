package com.hhs.order.entity;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * Product App과 통신을 통해 데이터를 담는 entity
 *
 */
@Getter
@Setter
public class ProductEntity {

    private int productId;

    private String name;

    private int amount;

    private long price;

    private long total;

}
