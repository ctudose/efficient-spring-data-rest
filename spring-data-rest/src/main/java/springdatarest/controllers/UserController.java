package springdatarest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springdatarest.exceptions.UserNotFoundException;
import springdatarest.model.User;
import springdatarest.repositories.UserRepository;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/users")
    List<User> findAll() {
        return repository.findAll();
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    User createUser(@RequestBody User user) {
        return repository.save(user);
    }

    @GetMapping("/users/{id}")
    User findUser(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PatchMapping("/users/{id}")
    User patchUser(@RequestBody Map<String, String> updates, @PathVariable Long id) {

        return repository.findById(id)
                .map(user -> {

                    String name = updates.get("name");
                    if (null != name) {
                        user.setName(name);
                    }

                    String isRegistered = updates.get("isRegistered");
                    if (null != isRegistered) {
                        user.setIsRegistered(isRegistered.equalsIgnoreCase("true") ? true : false);
                    }
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    throw new UserNotFoundException(id);
                });

    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
