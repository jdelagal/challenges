package ua.test.phonecatalog.catalogservice.model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "phones")
public class Phone {

	@Id
	private String id;

	@NotBlank
	private String name;

	@NotBlank
	private String description;

	@URL
	@NotBlank
	private String imageReference;

	@Positive
	private BigDecimal price;
	
	public Phone () {
		
	}

	public Phone(final String id, final String name, final String description,
			final String imageReference, final BigDecimal price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.imageReference = imageReference;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getImageReference() {
		return imageReference;
	}

	public void setImageReference(final String imageReference) {
		this.imageReference = imageReference;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(final BigDecimal price) {
		this.price = price;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Phone phone = (Phone) o;
		return Objects.equals(id, phone.id) && Objects.equals(name, phone.name)
				&& Objects.equals(description, phone.description)
				&& Objects.equals(imageReference, phone.imageReference)
				&& Objects.equals(price, phone.price);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, description, imageReference, price);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Phone{");
		sb.append("id=").append(id);
		sb.append(", name='").append(name).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", imageReference='").append(imageReference).append('\'');
		sb.append(", price=").append(price);
		sb.append('}');
		return sb.toString();
	}
}
