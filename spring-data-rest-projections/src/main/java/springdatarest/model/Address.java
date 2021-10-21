package springdatarest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address {

	@GeneratedValue
	@Id
	private Long id;
	private String street, zipCode, city, state;

	protected Address() {

	}

	public Address(String street, String zipCode, String city, String state) {
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
		this.state = state;
	}

	public String toString() {
		return String.format("%s, %s %s, %s", street, zipCode, city, state);
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getStreet() {
		return street;
	}

	public String getZipCode() {
		return zipCode;
	}
}
