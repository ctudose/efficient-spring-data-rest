package devtalks.springdatarest.events;

import devtalks.springdatarest.model.Person;
import org.apache.log4j.Logger;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.stereotype.Service;

@Service
public class RepositoryEventListener extends
        AbstractRepositoryEventListener<Person> {

    private final static Logger logger = Logger.getLogger(RepositoryEventListener.class);

    @Override
    public void onBeforeCreate(Person person) {
        logger.info("I'll make an effort to create this person " + person.getName());
    }

    @Override
    public void onAfterCreate(Person person) {
        logger.info("I did my best and I was able to create " + person.getName());
    }

    @Override
    public void onBeforeSave(Person person) {
        logger.info("I'll take a breath and I will save " + person.getName());
    }

    @Override
    public void onAfterSave(Person person) {
        logger.info("Hard, hard to save " + person.getName());
    }

    @Override
    public void onBeforeDelete(Person person) {
        logger.warn("I'll delete " + person.getName() + ", you might never see him/her again");
    }

    @Override
    public void onAfterDelete(Person person) {
        logger.warn("Say good-bye to " + person.getName() + ", I deleted him/her");
    }
}
