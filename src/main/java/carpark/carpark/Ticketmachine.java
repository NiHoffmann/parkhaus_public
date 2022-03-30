package carpark.carpark;

import java.util.Date;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

/**
 * Class for issues parking tickets, management is up to the carpark
 */
public class Ticketmachine{
    /**
     * The price to park for one hour
     */
    static float priceHour;

    /**Private constructor of Ticketmachine class*/
    private Ticketmachine(){

    }

    /**
     * Getter
     * @return The price for every hour stayed
     */
    public static float getPrice() {
        return priceHour;
    }


    public static float calculatePrice(Ticket ticket) {
        long delta = Ticketmachine.getHourDelta(ticket.getEntered(), ticket.getExited());

        return delta * priceHour;
    }

    /**
     * Calculates the delta of hours between two dates
     * @param past The first and lesser date
     * @param present The second and latter date
     * @return The difference in hours between past and present
     */
    public static long getHourDelta(Calendar past, Calendar present) {
        Date d1 = past.getTime();
        Date d2 = present.getTime();

        return ChronoUnit.HOURS.between(d1.toInstant(), d2.toInstant());
    }

    /**
     * Simulates putting a ticket into the Ticket machine
     * @param id Unique identifier
     * @param entered The custom time that the ticket was issued
     * @return The new ticket
     */
    public static Ticket generateTicket(String id, Calendar entered) {
        return  new Ticket(id, entered);
    }
    public static Ticket generateTicket(String id) {
        return generateTicket(id, Calendar.getInstance());
    }
    /**
     * Simulates putting a ticket into the Ticket machine
     * @param id Unique identifier
     * @param entered The custom date that the ticket was issued
     * @param exited The custom date that the ticket was used
     * @return The new ticket
     */
    public static Ticket generateTicket(String id, Calendar entered, Calendar exited) {
        return new Ticket(id, entered, exited);
    }

    /**
     * Simulates putting a ticket into the Ticket machine
     * @param ticket The ticket to use
     * @return The price of the stay
     */
    public static float useTicket(Ticket ticket) {
        float price = calculatePrice(ticket);

        ticket.use();

        return price;
    }
}