package carpark.carpark;

import java.util.Calendar;

class Ticket implements TicketInt {
    final String id;
    final Calendar entered;
    Calendar exited;
    boolean used = false;

    /**
     * Creates a ticket with current date
     * @param id Unique identifier
     */
    protected Ticket(String id) {
        this.id = id;
        this.entered = Calendar.getInstance();
    }

    /**
     * Creates a ticket with a custom time for entered
     * @param id Unique identifier
     * @param entered The custom date that the ticket was issued
     */
    protected Ticket(String id, Calendar entered) {
        this.id = id;
        this.entered = entered;
    }

    /**
     * Creates a ticket with a custom time for entered and exited
     * @param id Unique identifier
     * @param entered The custom date that the ticket was issued
     * @param exited The custom date that the ticket was used
     */
    protected Ticket(String id, Calendar entered, Calendar exited) {
        this.id = id;
        this.entered = entered;
        this.exited = exited;
    }

    /**
     * Getter
     * @return Id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Getter
     * @return Calendar date issued
     */
    public Calendar getEntered() {
        return this.entered;
    }

    /**
     * Getter
     * @return Calendar date used
     */
    public Calendar getExited() {
        return this.exited;
    }

    /**
     * Returns if the ticket has been used or not
     * @return If the ticket has been used or not
     */
    public boolean isUsed() {
        return this.used;
    }

    /**
     * Simulates the use of the ticket
     */
    public void use() {
        if (this.used)
            return;

        this.used = true;
    }
}