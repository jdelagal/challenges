package ua.test.phonecatalog.orderservice.controller;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.test.phonecatalog.orderservice.model.Order;
import ua.test.phonecatalog.orderservice.service.OrderService;

@RestController
@RequestMapping(value = "/api/orders")
public class OrderController {

	private final OrderService orderService;

	public OrderController(final OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Order> create(@Valid @RequestBody final Order order) {
		return this.orderService.save(order);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Flux<Order> all() {
		return this.orderService.find();
	}

}
