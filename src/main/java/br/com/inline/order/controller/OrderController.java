package br.com.inline.order.controller;

import br.com.inline.order.model.Order;
import br.com.inline.order.model.OrderRequest;
import br.com.inline.order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    private ResponseEntity createOrder(@RequestBody OrderRequest orderRequest)  {
        Order order = orderService.registerOrder(orderRequest.getProduct(), orderRequest.getAmount());
        return ResponseEntity.accepted().body(order);
    }
}
