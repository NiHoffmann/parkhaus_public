package carpark.carpark;

import java.util.Calendar;

public interface TicketInt {

    /**
     * Getter
     * @return Id
     */
    public String getId();

    /**
     * Getter
     * @return Calendar date issued
     */
    public Calendar getEntered();

    /**
     * Getter
     * @return Calendar date used
     */
    public Calendar getExited();

    /**
     * Returns if the ticket has been used or not
     * @return If the ticket has been used or not
     */
    public boolean isUsed();

    /**
     * Simulates the use of the ticket
     */
    public void use();
}
