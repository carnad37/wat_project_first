package com.hhs.order.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseEntity<T>{

    private String message;

    private T result;

}
