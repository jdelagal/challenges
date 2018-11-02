package ua.test.phonecatalog.catalogservice.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ua.test.phonecatalog.catalogservice.model.Phone;

public interface PhoneCatalogRepository extends ReactiveMongoRepository<Phone, String> {

}
