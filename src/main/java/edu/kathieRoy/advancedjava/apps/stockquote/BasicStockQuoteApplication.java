package edu.kathieRoy.advancedjava.apps.stockquote;


import edu.kathieRoy.advancedjava.model.*;
import edu.kathieRoy.advancedjava.service.*;


import java.text.ParseException;
import java.util.List;

/**
 * A simple application that shows the StockService in action.
 */
public class BasicStockQuoteApplication {

    private StockService stockService;

    private PersonService personService;

    // an example of how to use enum - not part of assignment 3 but useful for assignment 4

    /**
     * An enumeration that indicates how the program terminates (ends)
     */
    private enum ProgramTerminationStatusEnum {

        // for now, we just have normal or abnormal but could more specific ones as needed.
        NORMAL(0),
        ABNORMAL(-1);

        // when the program exits, this value will be reported to underlying OS
        private int statusCode;

        /**
         * Create a new  ProgramTerminationStatusEnum
         *
         * @param statusCodeValue the value to return the OS. A value of 0
         *                        indicates success or normal termination.
         *                        non 0 numbers indicate abnormal termination.
         */
        private ProgramTerminationStatusEnum(int statusCodeValue) {
            this.statusCode = statusCodeValue;
        }

        /**
         * @return The value sent to OS when the program ends.
         */
        private int getStatusCode() {
            return statusCode;
        }
    }

    /**
     * Create a new Application.
     *
     * @param personService the PersonService  this application instance should use for
     *                     person Stock Interests queries.
     */
    public BasicStockQuoteApplication(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Create a new Application.
     *
     * @param stockService the StockService this application instance should use for
     *                     stock queries.
     *                     <p/>
     *                     NOTE: this is a example of Dependency Injection in action.
     */
    public BasicStockQuoteApplication(StockService stockService) {
        this.stockService = stockService;
    }

    /**
     * Given a <CODE>stockQuery</CODE> get back a the info about the stock to display to th user.
     *
     * @param stockQuery the stock to get data for.
     * @return a String with the stock data in it.
     * @throws StockServiceException If data about the stock can't be retrieved. This is a
     *                               fatal error.
     */
    public String displayStockQuotes(StockQuery stockQuery) throws StockServiceException {
        StringBuilder stringBuilder = new StringBuilder();

        List<StockQuote> stockQuotes =
                stockService.getQuote(stockQuery.getSymbol(), stockQuery.getFrom(), stockQuery.getUntil());

        stringBuilder.append("Stock quotes for: " + stockQuery.getSymbol() + "\n");
        for (StockQuote stockQuote : stockQuotes) {
            stringBuilder.append(stockQuote.toString());
        }

        return stringBuilder.toString();
    }
    public String displayPersonStockInterests(Person person) throws PersonServiceException {
        StringBuilder stringBuilder = new StringBuilder();

            List<StockInterests> stockInterests =
                    personService.getStockInterests(person);

            stringBuilder.append("Stock Interests for: " + person.getFirstName() +
                    " " + person.getLastName() + "\n");
            for (StockInterests StockInterest : stockInterests) {
                stringBuilder.append(StockInterest.toString());
            }
        return stringBuilder.toString();
    }

    /**
     * Terminate the application.
     *
     * @param statusCode        an enum value that indicates if the program terminated ok or not.
     * @param diagnosticMessage A message to display to the user when the program ends.
     *                          This should be an error message in the case of abnormal termination
     *                          <p/>
     *                          NOTE: This is an example of DRY in action.
     *                          A program should only have one exit point. This makes it easy to do any clean up
     *                          operations before a program quits from just one place in the code.
     *                          It also makes for a consistent user experience.
     */
    private static void exit(ProgramTerminationStatusEnum statusCode, String diagnosticMessage) {
        if (statusCode == ProgramTerminationStatusEnum.NORMAL) {
            System.out.println(diagnosticMessage);
        } else if (statusCode == ProgramTerminationStatusEnum.ABNORMAL) {
            System.err.println(diagnosticMessage);
        } else {
            throw new IllegalStateException("Unknown ProgramTerminationStatusEnum.");
        }
        System.exit(statusCode.getStatusCode());
    }

    /**
     * Run the StockTicker application.
     * <p/>
     * When invoking the program supply one stock symbol, a from date and an end date also, if user supplied an interval.
     *
     * @param args one or more stock symbols
     */
    public static void main(String[] args) {

        // be optimistic init to positive values
        ProgramTerminationStatusEnum exitStatus = ProgramTerminationStatusEnum.NORMAL;
        String programTerminationMessage = "Normal program termination.";

        if (args.length != 4) {
            exit(ProgramTerminationStatusEnum.ABNORMAL,
                    "Please supply these arguments: a stock symbol, a start date (MM/DD/YYYY) and end date (MM/DD/YYYY) and an Interval");
        }
        try {
            Interval interval = findInterval(args[3]);
            StockQuery stockQuery = new StockQuery(args[0], args[1], args[2], interval);

            StockService stockService = ServiceFactory.getStockServiceInstance();
            BasicStockQuoteApplication basicStockQuoteApplication =
                    new BasicStockQuoteApplication(stockService);
            basicStockQuoteApplication.displayStockQuotes(stockQuery);
        } catch (ParseException e) {
            exitStatus = ProgramTerminationStatusEnum.ABNORMAL;
            programTerminationMessage = "Invalid date data: " + e.getMessage();
        } catch (StockServiceException e) {
            exitStatus = ProgramTerminationStatusEnum.ABNORMAL;
            programTerminationMessage = "StockService failed: " + e.getMessage();
        }

        try {
            PersonService personService = ServiceFactory.getPersonServiceInstance();
            BasicStockQuoteApplication basicPersonApplication =
                    new BasicStockQuoteApplication(personService);

            List<Person> personList = personService.getPerson();
            for (Person person : personList) {
                basicPersonApplication.displayPersonStockInterests(person);
            }
        } catch (PersonServiceException e) {
            exitStatus = ProgramTerminationStatusEnum.ABNORMAL;
            programTerminationMessage = "StockService failed: " + e.getMessage();
        }

        exit(exitStatus, programTerminationMessage);
     //   System.out.println("Oops could not parse a date");


    }

    /**
     * this method will take in the String arg (meant to be used with the run argument#4)
     * and determine which Enum corresponds to it.  If it does not find one, the default is DAY.
     *
     * @param arg
     * @return
     */
    public static Interval findInterval(String arg) {
        Interval returnInterval = Interval.DAY;
        for (Interval dir : Interval.values()) {
            if (dir.getsInterval().equals(arg)) {
                returnInterval = dir;
                break;
            }
        }
        return returnInterval;
    }
}
