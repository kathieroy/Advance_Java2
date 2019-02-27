package edu.kathieRoy.advancedjava.stockquote.model;

import edu.kathieRoy.advancedjava.model.Hobby;
import edu.kathieRoy.advancedjava.model.Person;
import edu.kathieRoy.advancedjava.model.PersonHobby;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for PersonHobby class
 */
public class PersonHobbyTest {

    /**
     * Testing helper method for generating PersonHobby test data
     *
     * @return a PersonHobby object that uses Person and Hobby
     * return from their respective create method.
     */
    public static PersonHobby createPersonHobby() {
        Person person = PersonTest.createPerson();
        Hobby hobby = HobbyTest.createHobby();
        return new PersonHobby(person, hobby);
    }

    @Test
    public void testPersonHobbiesGetterAndSetters() {
        Hobby hobby = HobbyTest.createHobby();
        Person person = PersonTest.createPerson();
        PersonHobby personHobby = new PersonHobby();
        int id = 10;
        personHobby.setId(id);
        personHobby.setPerson(person);
        personHobby.setHobby(hobby);
        assertEquals("person matches", person, personHobby.getPerson());
        assertEquals("hobby matches", hobby, personHobby.getHobby());
        assertEquals("id matches", id, personHobby.getId());
    }

    @Test
    public void testEqualsNegativeDifferentPerson() {
        PersonHobby personHobby = createPersonHobby();
        personHobby.setId(10);
        Hobby hobby = HobbyTest.createHobby();
        Person person = new Person();
        Timestamp birthDate = new Timestamp(PersonTest.birthDayCalendar.getTimeInMillis() + 10000);
        person.setBirthDate(birthDate);
        person.setFirstName(PersonTest.firstName);
        person.setLastName(PersonTest.lastName);
        PersonHobby personHobby2 = new PersonHobby(person, hobby);
        assertFalse("Different person", personHobby.equals(personHobby2));
    }

    @Test
    public void testEquals() {
        PersonHobby personHobby = createPersonHobby();
        assertTrue("Same objects are equal", personHobby.equals(createPersonHobby()));
    }

    @Test
    public void testToString() {
        PersonHobby personHobby = createPersonHobby();
        assertTrue("toString has lastName", personHobby.toString().contains(PersonTest.lastName));
        assertTrue("toString has person", personHobby.toString().contains(PersonTest.firstName));
        assertTrue("toString has hobby description", personHobby.toString().contains(HobbyTest.description));
        assertTrue("toString has hobby name", personHobby.toString().contains(HobbyTest.hobbyName));
        assertTrue("toString has hobby min age", personHobby.toString().contains(Integer.toString(HobbyTest.minimumAge)));
    }

}
