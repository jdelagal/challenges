package ua.test.phonecatalog.orderservice;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static ua.test.phonecatalog.orderservice.OrderUtils.createOrdersList;

import java.math.BigDecimal;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.test.phonecatalog.orderservice.controller.OrderController;
import ua.test.phonecatalog.orderservice.model.Order;
import ua.test.phonecatalog.orderservice.service.OrderService;

@RunWith(SpringRunner.class)
@WebFluxTest(OrderController.class)
public class OrderServiceApplicationTests {

	@Autowired
	private WebTestClient webClient;
	
	@MockBean
	private OrderService orderService;

	@Test
	public void create() {
		final Order order = createOrdersList().get(0);
	
		given(this.orderService.save(any(Order.class))).willReturn(Mono.just(order));
	
		webClient
			.post()
			.uri("/api/orders")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromObject(order))
			.exchange()
			.expectStatus().isCreated()
			.expectBody(Order.class)
			.isEqualTo(order);
	}

	@Test
	public void all() {
		final List<Order> phonesList = createOrdersList();
		final Order[] ordersArray = phonesList.toArray(new Order[phonesList.size()]);

		given(this.orderService.find()).willReturn(Flux.just(ordersArray));

		webClient
				.get()
				.uri("/api/orders")
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(Order.class)
				.hasSize(2).contains(ordersArray);
	}

}
