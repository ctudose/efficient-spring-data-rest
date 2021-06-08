package devtalks.springdatarest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "summary", types = Person.class)
public interface PersonProjection {

	String getFirstname();

	String getLastname();

	@Value("#{target.address.toString()}")
	String getAddress();
}
