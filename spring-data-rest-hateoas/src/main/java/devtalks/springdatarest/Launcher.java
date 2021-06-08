package devtalks.springdatarest;

import devtalks.springdatarest.model.Address;
import devtalks.springdatarest.model.Person;
import devtalks.springdatarest.repositories.AddressRepository;
import devtalks.springdatarest.repositories.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Launcher {

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }

    @Bean
    public CommandLineRunner init(PersonRepository personRepository, AddressRepository addressRepository)
    {
        return env ->
        {
            generateTestData(personRepository, addressRepository);
            System.out.println("TEST DATA GENERATED");
        };
    }

    private void generateTestData(PersonRepository repository, AddressRepository addressRepository)
    {
        Address address11 = addressRepository.save(new Address("Darwin Street", "Kiev"));
        Address address12 = addressRepository.save(new Address("Defence Street", "Kiev"));
        Address address13 = addressRepository.save(new Address("Distant street", "Kiev"));

        Address address21 = addressRepository.save(new Address("Gas Street", "Kiev"));
        Address address22 = addressRepository.save(new Address("Gastello Street", "Kiev"));
        Address address23 = addressRepository.save(new Address("Gogol street", "Kiev"));

        repository.save(new Person("Ivan", 21, address11));
        repository.save(new Person("Alex", 27, address12));
        repository.save(new Person("Tomas", 21, address13));
        repository.save(new Person("Irina", 18, address21));
        repository.save(new Person("Anna", 21, address22));

        repository.save(new Person("Toma", 35, address23));
        repository.save(new Person("Ben", 38, address23));
        repository.save(new Person("Mike", 16, address23));
        repository.save(new Person("Ibrahim", 39, address12));
    }


}
