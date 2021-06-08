package devtalks.springdatarest;

import devtalks.springdatarest.Person.Gender;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	@Autowired
	PersonRepository persons;

	@PostConstruct
	public void init() {

		persons.save(new Person("John", "Smith", Gender.MALE,
				new Address("3 Flowers Streets", "12345", "Boston", "MA")));

	}

	public static void main(String... args) {
		SpringApplication.run(Application.class, args);
	}


}
