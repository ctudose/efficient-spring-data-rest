package devtalks.springdatarest.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Room {

    private String roomNumber;
    private int seats;
    private Set<Person> persons = new HashSet<>();

    public Room(String roomNumber, int seats) {
        this.roomNumber = roomNumber;
        this.seats = seats;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Set<Person> getPersons() {
        return Collections.unmodifiableSet(persons);
    }

    public boolean addPerson(Person person) {
        if (persons.size() >= seats) {
            throw new RuntimeException("Cannot add more persons than the capacity of the room!");
        }
        return persons.add(person);
    }

    public boolean removePerson(Person person) {
        return persons.remove(person);
    }

    @Override
    public String toString() {
        return "Room " + getRoomNumber();
    }

}
