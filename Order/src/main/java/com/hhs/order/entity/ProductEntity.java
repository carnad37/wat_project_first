package com.hhs.order.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 *
 * Product App과 통신을 통해 데이터를 담는 entity
 *
 */
@Getter
@Setter
public class ProductEntity {

    private long productId;

    private String name;

    private long price;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private LocalDateTime deleteTime;

}
