package br.com.inline.order;

import br.com.inline.order.model.Order;
import br.com.inline.order.services.OrderService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.Duration;

@SpringBootTest
class TestApplicationTests {

	@Autowired
	private OrderService orderService;


	@Test
	void shouldCreateOrderWithoutTax() throws Exception {
		Order order =  orderService.createOrder("refri", BigDecimal.TEN);
		assertFalse(order.getTaxed());
	}

	@Test
	void shouldCreateOrderWithTax() throws Exception {
		Order order =  orderService.createOrder("beer", BigDecimal.TEN);
		assertTrue(order.getTaxed());
	}

	@Test
	void shouldSentEmail() throws Exception{
		Order orderBeer = orderService.createOrder("beer", BigDecimal.valueOf(4));
		Order order = orderService.sendEmail(orderBeer);
		assertTrue(order.getSentEmail());
	}

	@Test
	void shouldNotSentEmail() throws Exception {
		Order order = orderService.sendEmail(orderService.createOrder("refri", BigDecimal.valueOf(10)));
			assertFalse(order.getSentEmail());
	}

	@Test
	void shouldThrowException() {
		assertThrows(Exception.class, ()-> {
			orderService.createOrder("beer", BigDecimal.ZERO);
		});
	}

	@Test
	void shouldCreateOrderWithTaxAndAmountDiffZero() throws Exception {
		Order order =  orderService.createOrder("refri", BigDecimal.TEN);
		assertTrue(order.getTaxed());
		assertTrue(order.getAmount().compareTo(BigDecimal.ZERO) > 0 );
	}

	@Test
	void shouldCreateOrderWithTaxAndAmountDiffZeroAssertGroup() throws Exception {
		Order order =  orderService.createOrder("refri", BigDecimal.TEN);
		assertAll("Valid",
				() -> assertTrue(order.getTaxed()),
				() -> assertTrue(order.getAmount().compareTo(BigDecimal.ZERO) > 0 )
		);
	}

	@Test
	void shouldCreateOrderLess1Second() {
		assertTimeout(Duration.ofSeconds(1), () -> orderService.createOrder("refri", BigDecimal.TEN));
	}
}
