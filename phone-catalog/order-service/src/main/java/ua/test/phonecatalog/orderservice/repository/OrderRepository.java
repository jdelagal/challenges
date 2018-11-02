package ua.test.phonecatalog.orderservice.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ua.test.phonecatalog.orderservice.model.Order;

public interface OrderRepository extends ReactiveMongoRepository<Order, String> {

}
