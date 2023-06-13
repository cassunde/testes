package br.com.inline.order.repository;

import br.com.inline.order.model.Order;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class OrderRepository {

    private static List<Order> ALL_ORDERS = List.of(
            new Order("Refri", BigDecimal.TEN),
            new Order("Beer", BigDecimal.valueOf(12))
    );

    public List<Order> getOrders(String product, BigDecimal amount){
        return ALL_ORDERS.stream().filter( order ->
                order.getAmount().compareTo(amount) == 0
                && order.getProduct().equals(product) )
                .collect(Collectors.toList());
    }

    public Optional<Order> getOrdersByName(String product){
        return ALL_ORDERS.stream().filter( order -> order.getProduct().equals(product) )
                .findFirst();
    }
}
