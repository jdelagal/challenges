package ua.test.phonecatalog.catalogservice.service;

import java.util.List;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.test.phonecatalog.catalogservice.exceptions.NotFoundException;
import ua.test.phonecatalog.catalogservice.model.Phone;
import ua.test.phonecatalog.catalogservice.repository.PhoneCatalogRepository;
import org.json.*;

@Service
public class PhoneCatalogService {

	private final static Logger LOGGER = Logger.getLogger(PhoneCatalogService.class.getName());

	private final PhoneCatalogRepository phoneCatalogRepository;

	public PhoneCatalogService(final PhoneCatalogRepository phoneCatalogRepository) {
		this.phoneCatalogRepository = phoneCatalogRepository;
	}

	public Flux<Phone> find() {
		JSONObject obj = new JSONObject("mock");
		//return obj;
		return this.phoneCatalogRepository.findAll();
	}

	public Flux<Phone> find(final List<String> idSequence) {
		return this.phoneCatalogRepository.findAllById(idSequence)
				.switchIfEmpty(Mono.error(new NotFoundException(
				"Failed to retrieve the phones with given ids, idSeq=" + idSequence)));
	}

	public Mono<Phone> save(final Phone phone) {
		return this.phoneCatalogRepository.save(phone);
	}

}
