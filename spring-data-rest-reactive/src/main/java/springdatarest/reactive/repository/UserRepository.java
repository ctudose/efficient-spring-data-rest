package springdatarest.reactive.repository;

import springdatarest.reactive.model.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface UserRepository extends R2dbcRepository<User, Long> {

    Flux<User> findByName(String name);
}
