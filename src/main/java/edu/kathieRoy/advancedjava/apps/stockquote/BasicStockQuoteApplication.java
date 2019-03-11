package edu.kathieRoy.advancedjava.apps.stockquote;


import edu.kathieRoy.advancedjava.model.*;
import edu.kathieRoy.advancedjava.service.*;
import edu.kathieRoy.advancedjava.util.InvalidXMLException;
import edu.kathieRoy.advancedjava.util.XMLUtils;
import edu.kathieRoy.advancedjava.xml.Stocks;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * A simple application that shows the StockService in action.
 */
public class BasicStockQuoteApplication {

    private StockService stockService;

    private PersonService personService;
    private static final String pattern = "yyyy-MM-dd hh:mm:ss";
    private static String xmlInstance = "<stocks>\n" +
            " <stock symbol=\"VNET\" price=\"110.10\" time=\"2015-04-10 00:00:01\"> </stock>\n" +
            " <stock symbol=\"AKAM\" price=\"30.00\" time=\"2015-02-11 00:00:01\"> </stock>\n" +
            " <stock symbol=\"AOL\" price=\"40.98\" time=\"2015-07-12 00:00:01\"> </stock>\n" +
            " <stock symbol=\"BCOM\" price=\"55.50\" time=\"2015-09-13 00:00:01\"> </stock>\n" +
            " <stock symbol=\"BIDU\" price=\"10.45\" time=\"2015-06-11 00:00:01\"> </stock>\n" +
            " <stock symbol=\"BCOR\" price=\"76.23\" time=\"2015-05-12 00:00:01\"> </stock>\n" +
            " <stock symbol=\"WIFI\" price=\"50.65\" time=\"2015-09-13 00:00:01\"> </stock>\n" +
            " <stock symbol=\"BRNW\" price=\"100.10\" time=\"2015-04-14 00:00:01\"> </stock>\n" +
            " <stock symbol=\"CARB\" price=\"49.20\" time=\"2015-05-15 00:00:01\"> </stock>\n" +
            " <stock symbol=\"JRJC\" price=\"39.15\" time=\"2015-06-11 00:00:01\"> </stock>\n" +
            " <stock symbol=\"ANET\" price=\"54.13\" time=\"2015-03-12 00:00:01\"> </stock>\n" +
            " <stock symbol=\"VSET\" price=\"20.41\" time=\"2015-06-13 00:00:01\"> </stock>\n" +
            " <stock symbol=\"VNCT\" price=\"110.10\" time=\"2015-08-14 00:00:01\"> </stock>\n" +
            " <stock symbol=\"VNED\" price=\"160.10\" time=\"2015-02-15 00:00:01\"> </stock>\n" +
            " <stock symbol=\"ENET\" price=\"116.10\" time=\"2015-03-11 00:00:01\"> </stock>\n" +
            " <stock symbol=\"VFET\" price=\"13.10\" time=\"2015-04-12 00:00:01\"> </stock>\n" +
            " <stock symbol=\"VNGT\" price=\"10.10\" time=\"2015-05-13 00:00:01\"> </stock>\n" +
            " <stock symbol=\"VNEH\" price=\"20.10\" time=\"2015-06-14 00:00:01\"> </stock>\n" +
            " <stock symbol=\"INAT\" price=\"14.10\" time=\"2015-07-15 00:00:01\"> </stock>\n" +
            " <stock symbol=\"VJEB\" price=\"19.10\" time=\"2015-08-11 00:00:01\"> </stock>\n" +
            " <stock symbol=\"ANKT\" price=\"310.10\" time=\"2015-09-11 00:00:01\"> </stock>\n" +
            " <stock symbol=\"VBEL\" price=\"13.10\" time=\"2015-10-12 00:00:01\"> </stock>\n" +
            " <stock symbol=\"MNCT\" price=\"16.10\" time=\"2015-11-13 00:00:01\"> </stock>\n" +
            " <stock symbol=\"VNED\" price=\"18.10\" time=\"2015-12-14 00:00:01\"> </stock>\n" +
            " <stock symbol=\"ENOT\" price=\"290.80\" time=\"2015-02-15 00:00:01\"> </stock>\n" +
            " <stock symbol=\"VFEP\" price=\"70.55\" time=\"2015-03-16 00:00:01\"> </stock>\n" +
            " <stock symbol=\"QNGT\" price=\"80.70\" time=\"2015-04-11 00:00:01\"> </stock>\n" +
            " <stock symbol=\"VREH\" price=\"310.96\" time=\"2015-05-12 00:00:01\"> </stock>\n" +
            " <stock symbol=\"INST\" price=\"410.15\" time=\"2015-06-13 00:00:01\"> </stock>\n" +
            " <stock symbol=\"SJET\" price=\"60.40\" time=\"2015-07-11 00:00:01\"> </stock>\n" +
            " <stock symbol=\"UNKT\" price=\"450.17\" time=\"2015-08-10 00:00:01\"> </stock>\n" +
            " <stock symbol=\"VVEL\" price=\"22.20\" time=\"2015-02-11 00:00:01\"> </stock>\n" +
            " <stock symbol=\"MNWT\" price=\"190.90\" time=\"2015-02-11 00:00:01\"> </stock>\n" +
            " <stock symbol=\"VNEX\" price=\"60.70\" time=\"2015-03-12 00:00:01\"> </stock>\n" +
            " <stock symbol=\"YNOT\" price=\"88.80\" time=\"2015-03-13 00:00:01\"> </stock>\n" +
            " <stock symbol=\"VZEP\" price=\"11.33\" time=\"2015-04-14 00:00:01\"> </stock>\n" +
           " </stocks>";

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
/**
 * here, we will take the xml instance provided and
 * unmarshall it, then - convert it to database ORM objects (stockQuote) and persist to
 * the database.
 */
        Stocks stocks = null;
        // here is how to go from XML to Java
        try {
            XMLUtils.unmarshall(xmlInstance, Stocks.class);
            System.out.println(stocks.toString());
        } catch (InvalidXMLException e) {
            exitStatus = ProgramTerminationStatusEnum.ABNORMAL;
            programTerminationMessage = "XML could not be unmarshalled" + e.getMessage();
        }
        Date stockDate = new Date();
        List<Stocks.Stock> stockList = stocks.getStock();
        for (Stocks.Stock stock : stockList) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                stockDate = sdf.parse(stock.getTime());
            } catch (ParseException e) {
                exitStatus = ProgramTerminationStatusEnum.ABNORMAL;
                programTerminationMessage = "XML data info is invalid" + e.getMessage();
            }
            StockQuote stockQuote = new StockQuote(new BigDecimal(stock.getPrice()), stockDate, stock.getSymbol());
             //how to persist?
        }

        exit(exitStatus, programTerminationMessage);
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
