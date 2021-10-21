package springdatarest.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "summary", types = User.class)
public interface UserProjection {

	String getName();

	@Value("#{target.address.toString()}")
	String getAddress();
}
