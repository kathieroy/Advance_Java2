package edu.kathieRoy.advancedjava.stockquote.model;
import edu.kathieRoy.advancedjava.model.Person;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for the Person class
 */
public class PersonTest {

    public  static final Calendar birthDayCalendar = Calendar.getInstance();


    public static final String firstName = "Roy";
    public  static final String lastName = "Kathie";

    /**
     * Testing helper method for generating Person test data
     *
     * @return a Person object that uses static constants for data.
     */
    public static Person createPerson() {
        Person person = new Person();

        person.setFirstName(firstName);
        person.setLastName(lastName);
        return person;
    }

    @Test
    public void testPersonGettersAndSetters() {
        Person person = createPerson();
        int id = 10;
        person.setId(id);
        assertEquals("first name matches", firstName, person.getFirstName());
        assertEquals("last name matches", lastName, person.getLastName());
        assertEquals("id matches", id, person.getId());

    }

}
