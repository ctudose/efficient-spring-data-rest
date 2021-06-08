package devtalks.springdatarest.model;

import devtalks.springdatarest.exceptions.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private Map<String, Country> countriesMap;

    @GetMapping("/persons")
    List<Person> findAll() {
        return repository.findAll();
    }

    @PostMapping("/persons")
    @ResponseStatus(HttpStatus.CREATED)
    Person createPerson(@RequestBody Person person) {
        return repository.save(person);
    }

    @GetMapping("/persons/{id}")
    Person findPerson(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @PatchMapping("/persons/{id}")
    Person patchPerson(@RequestBody Map<String, String> updates, @PathVariable Long id) {

        return repository.findById(id)
                .map(person -> {

                    String name = updates.get("name");
                    if (null != name) {
                        person.setName(name);
                    }

                    Country country = countriesMap.get(updates.get("country"));
                    if (null != country) {
                        person.setCountry(country);
                    }

                    String isRegistered = updates.get("isRegistered");
                    if (null != isRegistered) {
                        person.setIsRegistered(isRegistered.equalsIgnoreCase("true") ? true : false);
                    }
                    return repository.save(person);
                })
                .orElseGet(() -> {
                    throw new PersonNotFoundException(id);
                });

    }

    @DeleteMapping("/persons/{id}")
    void deletePerson(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
