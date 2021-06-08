package devtalks.springdatarest.events;

import devtalks.springdatarest.model.Person;
import org.apache.log4j.Logger;
import org.springframework.data.rest.core.annotation.*;

@RepositoryEventHandler
public class PersonRepositoryEventHandler {

    private final static Logger logger = Logger.getLogger(PersonRepositoryEventHandler.class);

    @HandleBeforeCreate
    public void handlePersonBeforeCreate(Person person){
        if ((person.getName().toUpperCase().charAt(0) >= 'A') && (person.getName().toUpperCase().charAt(0) <= 'M')) {
            logger.info("Person " + person.getName() + " is to be created, goes to the first part of the alphabet");
        } else {
            logger.info("Person " + person.getName() + " is to be created, goes to the second part of the alphabet");
        }
    }

    @HandleAfterCreate
    public void handlePersonAfterCreate(Person person){
        logger.info("I am so tired to have created " + person.getName());
    }

    @HandleBeforeDelete
    public void handlePersonBeforeDelete(Person person){
        logger.warn("This is just to let you know that " + person.getName() + " is about to be deleted");
    }

    @HandleAfterDelete
    public void handlePersonAfterDelete(Person person){
        logger.warn("Sad but true, " + person.getName() + " has been deleted");
    }

}
