package com.example.services;

import com.example.errors.EntityNotFoundException;
import com.example.persistence.OrderEntity;
import com.example.persistence.springdata.OrderRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Transactional
public class OrderRepositoryService {

    private final OrderRepository orderRepository;

    public OrderRepositoryService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Iterable<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public Iterable<OrderEntity> findOrdersByEmail(String email) {
        return orderRepository.findByCustomerEmail(email);
    }

    public void createOrder(OrderEntity order) {
        orderRepository.save(order);
    }

    public OrderEntity findOrderById(Integer id) {
        Optional<OrderEntity> optOrder = orderRepository.findById(id);
        if (optOrder.isEmpty()) {
            throw new EntityNotFoundException("Order not found with ID" + id);
        }
        return optOrder.get();
    }
}
