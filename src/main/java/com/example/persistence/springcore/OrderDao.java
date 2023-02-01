package com.example.persistence.springcore;

import com.example.persistence.OrderEntity;

import java.util.List;

public interface OrderDao {
    void createOrder(OrderEntity entity);
    List<OrderEntity> getAllOrders();
    OrderEntity findOrderById(Integer id);
}
