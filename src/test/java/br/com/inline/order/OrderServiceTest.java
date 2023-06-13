package br.com.inline.order;

import br.com.inline.order.exception.OrderInvalidException;
import br.com.inline.order.model.Order;
import br.com.inline.order.repository.OrderRepository;
import br.com.inline.order.services.OrderService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
class OrderServiceTest {

	@Autowired
	private OrderService orderService;

	@MockBean
	private OrderRepository orderRepositoryMock;


	@Test
	void shouldCreateOrderWithoutTax() throws OrderInvalidException {
		Order order =  orderService.createOrder("refri", BigDecimal.TEN);
		assertFalse(order.getTaxed());
	}

	@Test
	void shouldCreateOrderWithTax() throws OrderInvalidException {
		Order order =  orderService.createOrder("beer", BigDecimal.TEN);
		assertTrue(order.getTaxed());
	}

	@Test
	void shouldCreateOrderWithTaxValid() throws OrderInvalidException {

//		OrderRepository orderRepositoryMock = Mockito.mock(OrderRepository.class);
//		Mockito.when(orderRepositoryMock.getOrdersByName("beer"))
//				.thenReturn(Optional.of(new Order("laranja", BigDecimal.TEN)));
		Mockito.when(orderRepositoryMock.getOrdersByName("beer"))
				.thenReturn(Optional.empty());
//		OrderService orderServiceMock = new OrderService(orderRepositoryMock2);

		Order order =  orderService.createOrder("beer", BigDecimal.TEN);
		assertTrue(order.getTaxed());
//		Mockito.verify(orderRepositoryMock).getOrders("xxx",BigDecimal.TEN);

		ArgumentCaptor<String> productCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
		Mockito.verify(orderRepositoryMock).getOrdersByName(productCaptor.capture());

		assertEquals("beer", productCaptor.getValue());

	}

	@Test
	void shouldSentEmail() throws OrderInvalidException{
		Order orderBeer = orderService.createOrder("beer", BigDecimal.valueOf(4));
		Order order = orderService.sendEmail(orderBeer);
		assertTrue(order.getSentEmail());
	}

	@Test
	void shouldNotSentEmail() throws OrderInvalidException {
		Order order = orderService.sendEmail(orderService.createOrder("refri", BigDecimal.valueOf(10)));
			assertFalse(order.getSentEmail());
	}
}
