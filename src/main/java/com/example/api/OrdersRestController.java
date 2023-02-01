package com.example.api;

import com.example.dto.OrderDTO;
import com.example.persistence.OrderEntity;
import com.example.services.OrderRepositoryService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class OrdersRestController {

    /*
        private final OrderService orderService;

        public OrdersRestController(OrderService orderService) {
            this.orderService = orderService;
        }
     */

    private final OrderRepositoryService orderService;

    public OrdersRestController(OrderRepositoryService orderService) {
        this.orderService = orderService;
    }


    @GetMapping(value = "/orders")
    @ResponseStatus(HttpStatus.OK)
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public List<OrderDTO> getOrders(Authentication authentication, @RequestParam Optional<String> email) {
        System.out.println("Received request from '" + authentication.getName() + "'  with roles " + authentication.getAuthorities());

        Iterable<OrderEntity> result = email.isEmpty()
            ? orderService.getAllOrders()
            : orderService.findOrdersByEmail(email.get());

        List<OrderDTO> dtos = new ArrayList<>();
        for (OrderEntity entity : result) {
            dtos.add(OrderDTO.of(entity));
        }

        return dtos;
    }

    @GetMapping(value = "/orders/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDTO getOrderById(@PathVariable("orderId") Integer orderId) {
        return OrderDTO.of(orderService.findOrderById(orderId));
    }

    @PostMapping(value = "/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderDTO newOrderDTO) {
        OrderEntity newOrder = newOrderDTO.toEntity();
        orderService.createOrder(newOrder);
    }
}
