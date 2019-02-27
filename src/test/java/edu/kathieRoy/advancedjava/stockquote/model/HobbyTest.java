package edu.kathieRoy.advancedjava.stockquote.model;

import edu.kathieRoy.advancedjava.model.Hobby;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for Hobby class
 */
public class HobbyTest {

    final static String description = "ride on a skateboard";
    final static int minimumAge = 10;
    final static String hobbyName = "SkateBoarding";

    /**
     * Testing helper method for generating Hobby test data
     *
     * @return a Hobby object that uses static constants for data.
     */
    public static Hobby createHobby() {
        Hobby hobby = new Hobby();
        hobby.setDescription(description);
        hobby.setMinimumAge(minimumAge);
        hobby.setName(hobbyName);
        return hobby;
    }

    @Test
    public void testHobbySettersAndGetters() {
        Hobby hobby = createHobby();
        int id = 10;
        hobby.setId(id);
        assertEquals("Minimum age", minimumAge, hobby.getMinimumAge());
        assertEquals("Description", description, hobby.getDescription());
        assertEquals("Name", hobbyName, hobby.getName());
        assertEquals("id", id, hobby.getId());

    }

}
