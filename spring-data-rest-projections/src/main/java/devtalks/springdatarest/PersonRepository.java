package devtalks.springdatarest;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = PersonProjection.class)
public interface PersonRepository extends CrudRepository<Person, Long> {

}
