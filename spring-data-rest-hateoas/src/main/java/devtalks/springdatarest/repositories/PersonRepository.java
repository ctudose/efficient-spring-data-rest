package devtalks.springdatarest.repositories;

import devtalks.springdatarest.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
