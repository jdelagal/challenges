package ua.test.phonecatalog.orderservice;

import java.math.BigDecimal;
import java.util.List;
import ua.test.phonecatalog.orderservice.model.Order;
import ua.test.phonecatalog.orderservice.model.OrderDetails;

public class OrderUtils {

	static List<Order> createOrdersList() {
		return List.of(
				createOrder("John", "Doe", "john@doe.com", List.of(new OrderDetails("1", 2), new OrderDetails("2", 1))),
				createOrder("Johanna", "Doe", "johanna@doe.com", List.of(new OrderDetails("3", 1), new OrderDetails("4", 3)))
		);
	}

	static Order createOrder(String customerName, String customerSurname, String email, List<OrderDetails> phoneIds) {
		return new Order(String.valueOf(System.currentTimeMillis()), customerName, customerSurname, email, phoneIds, BigDecimal.ZERO);
	}

}
