package springdatarest.reactive.controller;

import springdatarest.reactive.model.User;
import springdatarest.reactive.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public Flux<User> list(@RequestParam(defaultValue = "0") Long start,
                           @RequestParam(defaultValue = "3") Long count) {
        return userRepository.findAll()
                .skip(start).take(count);
    }

    @GetMapping(path = "/all", produces = "text/event-stream")
    public Flux<List<String>> getAll() {
        return getStream()
                .buffer(5);
    }

    @GetMapping(path = "/stream", produces = "text/event-stream")
    public Flux<String> getStream() {
        return userRepository
                .findAll()
                .map(p -> p.getName())
                .delayElements(Duration.ofSeconds(1));
    }

    @GetMapping("/names")
    public Flux<String> names(@RequestParam(defaultValue = "0") Long start,
                              @RequestParam(defaultValue = "3") Long count) {
        return userRepository
                .findAll()
                .skip(start)
                .map(p -> p.getName() + " ");
    }

    @GetMapping("/name")
    public Flux<String> name(@RequestParam(defaultValue = "") String name) {
        return userRepository
                .findByName(name)
                .map(User::toString);
    }

}
