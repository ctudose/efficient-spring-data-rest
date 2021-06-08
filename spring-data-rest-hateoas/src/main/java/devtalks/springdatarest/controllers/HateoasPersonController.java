package devtalks.springdatarest.controllers;

import devtalks.springdatarest.controllers.assemblers.PersonModelAssembler;
import devtalks.springdatarest.exceptions.PersonNotFoundException;
import devtalks.springdatarest.model.Person;
import devtalks.springdatarest.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/persons")
public class HateoasPersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonModelAssembler personModelAssembler;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public CollectionModel<EntityModel<Person>> findAll() {

        return personModelAssembler.toCollectionModel(personRepository.findAll());
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public EntityModel<Person> findById(@PathVariable Long id) {

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        return personModelAssembler.toModel(person);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleExceptions(PersonNotFoundException e)
    {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

}
