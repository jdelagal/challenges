package ua.test.phonecatalog.orderservice;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static ua.test.phonecatalog.orderservice.OrderUtils.createOrder;

import java.math.BigDecimal;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.test.phonecatalog.orderservice.http.HttpClient;
import ua.test.phonecatalog.orderservice.model.Order;
import ua.test.phonecatalog.orderservice.model.OrderDetails;
import ua.test.phonecatalog.orderservice.model.Phone;
import ua.test.phonecatalog.orderservice.service.OrderService;

@RunWith(SpringRunner.class)
public class OrderServiceTest {

	@MockBean
	private HttpClient httpClient;

	@MockBean
	private OrderService orderService;

	@Test
	public void saveOrder_verifiesOrderTotalPrice() {
		final Phone phone1 = new Phone("1", "Samsung Galaxy S9", BigDecimal.valueOf(100));
		final Integer phone1Amount = 1;
		final Phone phone2 = new Phone("2", "iPhone 8 Plus", BigDecimal.valueOf(120));
		final Integer phone2Amount = 2;

		final Order order = createOrder(
				"John",
				"Doe",
				"john@doe.com",
				List.of(new OrderDetails(phone1.getId(), phone1Amount), new OrderDetails(phone2.getId(), phone2Amount)));

		given(this.orderService.save(any(Order.class)))
				.willReturn(Mono.just(order));

		given(this.httpClient.retrievePhones(any(List.class))).willReturn(Flux.just(phone1, phone2));

		final BigDecimal phone1Total = phone1.getPrice().multiply(BigDecimal.valueOf(phone1Amount));
		final BigDecimal phone2Total = phone2.getPrice().multiply(BigDecimal.valueOf(phone2Amount));

		this.orderService.save(order).doOnSuccess(savedOrder ->
				assertEquals(savedOrder.getTotalPrice(), phone1Total.add(phone2Total)));
	}

}
