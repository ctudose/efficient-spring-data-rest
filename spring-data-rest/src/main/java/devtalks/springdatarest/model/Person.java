package devtalks.springdatarest.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Person {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToOne
    private Country country;
    private boolean isRegistered;
    private boolean isUSACitizen;

    // avoid "No default constructor for entity"
    public Person() {

    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

    public boolean isUSACitizen() {
        return isUSACitizen;
    }

    public void setUSACitizen(boolean USACitizen) {
        isUSACitizen = USACitizen;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", country=" + country +
                ", registered=" + isRegistered +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return isRegistered == person.isRegistered &&
                Objects.equals(name, person.name) &&
                Objects.equals(country, person.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, isRegistered);
    }

}