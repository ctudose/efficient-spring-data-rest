package devtalks.springdatarest.controllers;

import devtalks.springdatarest.exceptions.PersonNotFoundException;
import devtalks.springdatarest.model.Address;
import devtalks.springdatarest.repositories.AddressRepository;
import devtalks.springdatarest.repositories.PersonRepository;
import devtalks.springdatarest.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    /**
     * Use Accept application/json or application/xml
     * to select what you want to receive.
     */
    //@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @GetMapping(value = "/{id:[0-9]+}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Person findById(@PathVariable Long id) {

        return personRepository
                .findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @GetMapping("/find")
    public List<Person> findByName(@RequestParam String name) {

        if (name.isBlank()) {
            throw new IllegalArgumentException("Name should not be null or empty");
        }

        return personRepository.findAll()
                .stream()
                .filter(p -> p.getName().toLowerCase()
                        .contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        personRepository.deleteById(id);
    }

    @PatchMapping("/{id}")
    public Person updatePerson(@RequestBody Person person, @PathVariable Long id) {
        Person existingPerson = personRepository
                .findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        existingPerson.setName(person.getName());
        existingPerson.setAge(person.getAge());

        return personRepository.save(existingPerson);
    }

    @PutMapping("/{id}")
    public Person replacePerson(@RequestBody Person person, @PathVariable Long id) {
        personRepository
                .findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        person.setId(id);
        return personRepository.save(person);
    }

    @PostMapping
    public Person addPerson(@RequestBody Person person) {
        Address address = person.getAddress();

        if (address != null) {
            address = addressRepository.save(address);
        }
        person.setAddress(address);

        return personRepository.save(person);
    }

    @ExceptionHandler({PersonNotFoundException.class, IllegalArgumentException.class})
    public ResponseEntity<String> handleExceptions(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
