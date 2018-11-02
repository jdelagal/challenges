package ua.test.phonecatalog.orderservice.http;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import ua.test.phonecatalog.orderservice.model.Phone;

@Component
public class HttpClient {

	private final static Logger LOGGER = Logger.getLogger(HttpClient.class.getName());

	private final WebClient webClient;
	
	public HttpClient(@Value("${phone-catalog.service.url}") final String phoneCatalogServiceUrl) {
		this.webClient = WebClient.builder()
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.baseUrl(phoneCatalogServiceUrl)
				.build();
	}
	
	public Flux<Phone> retrievePhones(final List<String> idSeq) {
		return this.webClient
				.get()
				.uri(queryBuilder -> queryBuilder
						.path("/api/phones")
						.queryParams(new LinkedMultiValueMap<>(Map.of("idSeq", idSeq)))
						.build())
				.retrieve()
				.bodyToFlux(Phone.class)
				.doOnError(error -> LOGGER.severe("Failed to retrieve phones data with given ids, idSeq=" + idSeq));
	}
	
}
