package devtalks.springdatarest.beans;

import devtalks.springdatarest.model.Country;
import devtalks.springdatarest.model.Room;
import devtalks.springdatarest.model.Person;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CsvDataLoader {

    private Map<String, Country> countriesMap = new HashMap<>();

    public CsvDataLoader() throws IOException {
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(
                             "src/main/resources/countries_information.csv"))) {
            String line = null;
            do {
                line = reader.readLine();
                if (line != null) {
                    String[] countriesString = line.toString().split(";");
                    Country country = new Country(countriesString[0].trim(), countriesString[1].trim());
                    countriesMap.put(countriesString[1].trim(), country);
                }
            } while (line != null);
        }
    }

    @Bean
    Map<String, Country> getCountriesMap() {
        return Collections.unmodifiableMap(countriesMap);
    }

    @Bean
    public Room buildRoomFromCsv() throws IOException {
        Room room = new Room("AA1234", 20);
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/persons_information.csv"))) {
            String line = null;
            do {
                line = reader.readLine();
                if (line != null) {
                    String[] personString = line.toString().split(";");
                    Person person = new Person(personString[0].trim());
                    person.setCountry(countriesMap.get(personString[1].trim()));
                    person.setIsRegistered(false);
                    room.addPerson(person);
                }
            } while (line != null);

        }

        return room;
    }
}
