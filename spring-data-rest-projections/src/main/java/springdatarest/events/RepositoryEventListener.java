package springdatarest.events;

import org.apache.log4j.Logger;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.stereotype.Service;
import springdatarest.model.User;

@Service
public class RepositoryEventListener extends
        AbstractRepositoryEventListener<User> {

    private final static Logger logger = Logger.getLogger(RepositoryEventListener.class);

    @Override
    public void onBeforeCreate(User user) {
        logger.info("I'll make an effort to create this user " + user.getName());
    }

    @Override
    public void onAfterCreate(User user) {
        logger.info("I did my best and I was able to create " + user.getName());
    }

    @Override
    public void onBeforeSave(User user) {
        logger.info("I'll take a breath and I will save " + user.getName());
    }

    @Override
    public void onAfterSave(User user) {
        logger.info("Hard, hard to save " + user.getName());
    }

    @Override
    public void onBeforeDelete(User user) {
        logger.warn("I'll delete " + user.getName() + ", you might never see him/her again");
    }

    @Override
    public void onAfterDelete(User user) {
        logger.warn("Say good-bye to " + user.getName() + ", I deleted him/her");
    }
}
