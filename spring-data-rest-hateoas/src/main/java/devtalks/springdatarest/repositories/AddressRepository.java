package devtalks.springdatarest.repositories;

import devtalks.springdatarest.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
