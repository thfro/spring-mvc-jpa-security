package com.example.services;

import com.example.errors.EntityNotFoundException;
import com.example.persistence.OrderEntity;
import com.example.persistence.springcore.OrderDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@Transactional
public class OrderService {

    private final Log log = LogFactory.getLog(getClass());

    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @PostConstruct
    public void init() {
        createTestOrders();
    }

    public List<OrderEntity> getAllOrders() {
        return orderDao.getAllOrders();
    }

    public void createOrder(OrderEntity order) {
        orderDao.createOrder(order);
    }

    public OrderEntity getOrderById(Integer id) {
        try {
            return orderDao.findOrderById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException("Order not found with ID " + id);
        }
    }


    private void createTestOrders() {
        var newOrder = new OrderEntity();
        newOrder.setCustomerEmail("customer-1@example.org");
        newOrder.setCustomerMobile("0170 - 12345678");
        orderDao.createOrder(newOrder);

        newOrder = new OrderEntity();
        newOrder.setCustomerEmail("customer-2@example.org");
        newOrder.setCustomerMobile("0170 - 87654321");
        orderDao.createOrder(newOrder);
        log.info("*********** Created test dto.");
    }
}
