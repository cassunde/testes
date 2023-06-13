package br.com.inline.order.services;

import br.com.inline.order.exception.OrderInvalidException;
import br.com.inline.order.model.Order;
import br.com.inline.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(String product, BigDecimal amount) throws OrderInvalidException {

        if(orderRepository.getOrdersByName(product).isPresent()){
            throw new OrderInvalidException();
        }

        if(product.equals("beer")){
            System.out.println("orde taxada");
            return new Order(product, amount, true);
        }
        System.out.println("order criada");
        return new Order(product, amount);
    }

    public Order registerOrder(String product, BigDecimal amount) throws OrderInvalidException {
        Order order = createOrder(product, amount);
        sendEmail(order);
        return order;
    }

    public Order sendEmail(Order order) {
        if (order.getTaxed() && order.getAmount().compareTo(BigDecimal.valueOf(5)) < 0) {
            System.out.println("send email: ");
            order.setSentEmail(true);
            return order;
        }
        return order;
    }
}
