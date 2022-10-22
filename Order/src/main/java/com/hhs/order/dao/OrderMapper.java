package com.hhs.order.dao;

import com.hhs.order.entity.OrderEntity;
import com.hhs.order.entity.OrderSearchEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    List<OrderEntity> select(OrderSearchEntity searchEntity);

    int insert(OrderEntity order);

    int delete(OrderEntity order);

}
