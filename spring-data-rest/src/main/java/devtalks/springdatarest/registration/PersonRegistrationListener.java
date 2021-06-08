package devtalks.springdatarest.registration;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class PersonRegistrationListener {

    @EventListener
    public void confirmRegistration(PersonRegistrationEvent personRegistrationEvent) {
        personRegistrationEvent.getPerson().setIsRegistered(true);
        System.out.println("Confirming the registration for the person: "
                + personRegistrationEvent.getPerson());
    }

    @EventListener(condition = "#personRegistrationEvent.person.country.name=='USA'")
    public void confirmRegistrationUSA(PersonRegistrationEvent personRegistrationEvent) {
        personRegistrationEvent.getPerson().setUSACitizen(true);
        System.out.println("Confirming the registration for the USA person: "
                + personRegistrationEvent.getPerson());
    }
}
