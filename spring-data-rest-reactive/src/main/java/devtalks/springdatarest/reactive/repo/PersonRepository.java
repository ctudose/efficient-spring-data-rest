package devtalks.springdatarest.reactive.repo;

import devtalks.springdatarest.reactive.model.Person;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface PersonRepository extends R2dbcRepository<Person, Long> {

    Flux<Person> findByName(String name);
}
