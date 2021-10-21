package springdatarest.events;

import org.apache.log4j.Logger;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Service;
import springdatarest.model.User;

@RepositoryEventHandler
@Service
public class UserRepositoryEventHandler {

    private final static Logger logger = Logger.getLogger(UserRepositoryEventHandler.class);

    @HandleBeforeCreate
    public void handleUserBeforeCreate(User user) {
        if ((user.getName().toUpperCase().charAt(0) >= 'A') && (user.getName().toUpperCase().charAt(0) <= 'M')) {
            logger.info("User " + user.getName() + " is to be created, goes to the first part of the alphabet");
        } else {
            logger.info("User " + user.getName() + " is to be created, goes to the second part of the alphabet");
        }
    }

    @HandleAfterCreate
    public void handleUserAfterCreate(User user) {
        logger.info("I am so tired to have created " + user.getName());
    }

    @HandleBeforeDelete
    public void handleUserBeforeDelete(User user) {
        logger.warn("This is just to let you know that " + user.getName() + " is about to be deleted");
    }

    @HandleAfterDelete
    public void handleUserAfterDelete(User user) {
        logger.warn("Sad but true, " + user.getName() + " has been deleted");
    }

}
