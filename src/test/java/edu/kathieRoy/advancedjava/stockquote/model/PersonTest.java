package edu.kathieRoy.advancedjava.stockquote.model;
import edu.kathieRoy.advancedjava.model.Person;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Unit test for the Person class
 */
public class PersonTest {

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
    /**
     * check that the equals method in Person works
     * by comparing two object that are the same
     */
    @Test
    public void testEqual() {
        Person person = new Person(10,"Nick","Roy");
        assertTrue("checking equals",person.equals(person));
        assertTrue(person.hashCode() == person.hashCode());

    }
    /**
     * check that the equals method in Person works
     * by comparing two object that are the same
     */
    @Test
    public void testNotEqual() {
        Person person = new Person(10,"Nick","Roy");
        Person person2 = new Person(12,"Melissa","Morin");
        assertFalse("checking not equal",person.equals(person2));
        assertFalse(person.hashCode() == person2.hashCode());
    }
}
