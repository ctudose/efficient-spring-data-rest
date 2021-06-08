package devtalks.springdatarest;

import javax.persistence.*;

@Entity
public class Person {

	@GeneratedValue
	@Id
	private Long id;
	private String firstname, lastname;
	private Gender gender;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Address address;

	public Person() {

	}

	public Person(String firstname, String lastname, Gender gender, Address address) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.address = address;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	static enum Gender {
		MALE, FEMALE;
	}
}
