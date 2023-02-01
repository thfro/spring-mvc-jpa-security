package com.example.persistence.springdata;

import com.example.persistence.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {
    Iterable<OrderEntity> findByCustomerEmail(String email);
}
