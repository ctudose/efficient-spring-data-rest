package devtalks.springdatarest.reactive.controller;

import devtalks.springdatarest.reactive.model.Person;
import devtalks.springdatarest.reactive.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public Flux<Person> list(@RequestParam(defaultValue = "0") Long start,
                             @RequestParam(defaultValue = "3") Long count) {
        return personRepository.findAll()
                .skip(start).take(count);
    }

    @GetMapping(path = "/all", produces = "text/event-stream")
    public Flux<List<String>> getAll() {
        return getStream()
                .buffer(5);
    }

    @GetMapping(path = "/stream", produces = "text/event-stream")
    public Flux<String> getStream() {
        return personRepository
                .findAll()
                .map(p -> p.getName())
                .delayElements(Duration.ofSeconds(1));
    }

    @GetMapping("/names")
    public Flux<String> names(@RequestParam(defaultValue = "0") Long start,
                              @RequestParam(defaultValue = "3") Long count) {
        return personRepository
                .findAll()
                .skip(start)
                .map(p -> p.getName() + " ");
    }

    @GetMapping("/name")
    public Flux<String> name(@RequestParam(defaultValue = "") String name) {
        return personRepository
                .findByName(name)
                .map(Person::toString);
    }

}
