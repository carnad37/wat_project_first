package com.hhs.product.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseEntity<T>{

    private String message;

    private T result;

}
