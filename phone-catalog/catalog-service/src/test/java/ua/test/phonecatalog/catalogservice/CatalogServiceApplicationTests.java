package ua.test.phonecatalog.catalogservice;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
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
import ua.test.phonecatalog.catalogservice.controller.PhoneCatalogController;
import ua.test.phonecatalog.catalogservice.model.Phone;
import ua.test.phonecatalog.catalogservice.service.PhoneCatalogService;

@RunWith(SpringRunner.class)
@WebFluxTest(PhoneCatalogController.class)
public class CatalogServiceApplicationTests {

	@Autowired
	private WebTestClient webClient;

	@MockBean
	private PhoneCatalogService phoneCatalogService;

	@Test
	public void all() {
		final List<Phone> phonesList = createPhonesList();
		final Phone[] phonesArray = phonesList.toArray(new Phone[phonesList.size()]);

		given(this.phoneCatalogService.find()).willReturn(Flux.just(phonesArray));

		webClient
				.get()
				.uri("/api/phones")
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(Phone.class)
				.hasSize(4).contains(phonesArray);
	}

	@Test
	public void allIn() {
		final List<Phone> phonesList = createPhonesList();

		final Phone phone0 = phonesList.get(0);
		final String phone0Id = phone0.getId();

		final Phone phone1 = phonesList.get(1);
		final String phone1Id = phone1.getId();

		final Phone phone2 = phonesList.get(2);
		final Phone phone3 = phonesList.get(3);

		given(this.phoneCatalogService.find(any(List.class))).willReturn(Flux.just(phone0, phone1));

		webClient
				.get()
				.uri("/api/phones?idSeq={idSeq}", phone0Id, phone1Id)
				.accept(MediaType.APPLICATION_JSON)
				.exchange().expectStatus().isOk()
				.expectBodyList(Phone.class)
				.hasSize(2)
				.contains(phone0, phone1)
				.doesNotContain(phone2, phone3);
	}

	@Test
	public void create() {
		final Phone phone =
				createPhone("LG V20", "LG V20 description", "http://image-store.com/lg-image.png");

		given(this.phoneCatalogService.save(any(Phone.class))).willReturn(Mono.just(phone));

		webClient
				.post()
				.uri("/api/phones")
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromObject(phone))
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Phone.class).isEqualTo(phone);
	}

	private List<Phone> createPhonesList() {
		return List.of(
				createPhone("Samsung Galaxy S9", "Samsung Galaxy S9 description",
						"https://imagestore.com/samsung-image.png"),
				createPhone("Huawei P20", "Huawei P20 description",
						"https://imagestore.com/huawei-image.png"),
				createPhone("HTC 10", "HTC 10 description", "https://imagestore.com/htc-image.png"),
				createPhone("iPhone X", "iPhone X description.", "https://imagestore.com/iphone-image.png")
		);
	}

	private Phone createPhone(String name, String description, String photoUrl) {
		return new Phone(String.valueOf(System.currentTimeMillis()), name, description, photoUrl,
				BigDecimal.valueOf(new Random(400).nextDouble()));
	}

}
