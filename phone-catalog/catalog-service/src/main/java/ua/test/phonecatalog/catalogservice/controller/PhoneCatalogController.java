package ua.test.phonecatalog.catalogservice.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.test.phonecatalog.catalogservice.model.Phone;
import ua.test.phonecatalog.catalogservice.service.PhoneCatalogService;

@RestController
@RequestMapping(value = "/api/phones")
public class PhoneCatalogController {

	private final PhoneCatalogService phoneCatalogService;

	public PhoneCatalogController(final PhoneCatalogService phoneCatalogService) {
		this.phoneCatalogService = phoneCatalogService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Flux<Phone> all() {
		return this.phoneCatalogService.find();
	}

	@GetMapping(params = "idSeq")
	@ResponseStatus(HttpStatus.OK)
	public Flux<Phone> allIn(@RequestParam(value = "idSeq") final List<String> idSequence) {
		return this.phoneCatalogService.find(idSequence);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Phone> create(@Valid @RequestBody final Phone phone) {
		return this.phoneCatalogService.save(phone);
	}

}
