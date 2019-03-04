package edu.kathieRoy.advancedjava.stockquote.util;

import edu.kathieRoy.advancedjava.util.DatabaseInitializationException;
import edu.kathieRoy.advancedjava.util.DatabaseUtils;
import org.hibernate.Session;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *  Tests for the DatabaseUtils class
 */
public class DatabaseUtilsTest {
    /**
     * This test will take an initialization file and create a db, then add rows to it.
     *
     * @throws Exception
     */
    @Test
    public void testGoodInitFile() throws Exception {
        DatabaseUtils.initializeDatabase(DatabaseUtils.initializationFile);
    }

    /**
     * This test will verify that the connection to the
     * database can be made using the getConnection method.
     * @throws Exception
     */
    @Test
    public void testGetConnection() throws Exception{
        Connection connection = DatabaseUtils.getConnection();
        assertNotNull("verify that we can get a connection ok",connection);
    }


    /**
     * This test will verify that the sql statement can be executed
     * once the connection has been made.
     * @throws Exception
     */
    @Test
    public void testGetConnectionWorks() throws Exception{
        Connection connection = DatabaseUtils.getConnection();
        Statement statement = connection.createStatement();
        boolean execute = statement.execute("select * from quotes");
        assertTrue("verify that we can execute a statement",execute);
    }



    /**
     * this test will make sure that a DatabaseInitialization exception will
     * be thrown if the database cannot be initialized. (again, taken from the solution)
     * @throws Exception
     */
    @Test(expected = DatabaseInitializationException.class)
    public void testBadInitFile() throws Exception {
        DatabaseUtils.initializeDatabase("bogus");
    }

    /**
     * this is a test to a method not yet used
     * if we want to use the method to execute an sql statement, it is here
     * and by testing it, we know if works
     * @throws Exception
     */
    @Test
    public void testSomeSql() throws Exception {
        DatabaseUtils.executeSQL("select * from quotes");
    }

    /**
     * Test the getSessionFactory method
     */
    @Test
    public void getSessionFactory() {
        Session session = DatabaseUtils.getSessionFactory().openSession();
        assertNotNull(session);
    }
}
