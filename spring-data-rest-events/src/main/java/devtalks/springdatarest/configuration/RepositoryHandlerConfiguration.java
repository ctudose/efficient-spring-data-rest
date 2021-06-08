package devtalks.springdatarest.configuration;

import devtalks.springdatarest.events.PersonRepositoryEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryHandlerConfiguration {

    @Bean
    public PersonRepositoryEventHandler personRepositoryEventHandler() {
        return new PersonRepositoryEventHandler();
    }
}
