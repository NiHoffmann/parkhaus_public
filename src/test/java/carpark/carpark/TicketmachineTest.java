package carpark.carpark;

import org.junit.jupiter.api.Test;
import java.util.Calendar;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketmachineTest {

    @Test
    void getPriceTest() {
        Ticketmachine.priceHour = 100.0f;
        assertEquals(100.0f, Ticketmachine.getPrice());
    }

    @Test
    void calculatePriceTest() {
        Ticketmachine.priceHour = 100.0f;
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2021,Calendar.JULY,22,16,0);
        Calendar cal2 = Calendar.getInstance();
        cal2.set(2021,Calendar.JULY,22,17,0);
        Ticket ticket = new Ticket("87", cal1, cal2);
        assertEquals(100.0f, Ticketmachine.calculatePrice(ticket));
    }

    @Test
    void getHourDeltaTest() {
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2021,Calendar.JULY,22,16,0);
        Calendar cal2 = Calendar.getInstance();
        cal2.set(2021,Calendar.JULY,22,18,0);

        assertEquals(2, Ticketmachine.getHourDelta(cal1, cal2));
    }

    @Test
    void generateTicketTest() {
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2021,Calendar.JULY,22,16,0);

        assertEquals("22", Ticketmachine.generateTicket("22", cal1).getId());
        assertEquals(cal1, Ticketmachine.generateTicket("22", cal1).getEntered());
    }

    @Test
    void useTicketTest() {
        Ticketmachine.priceHour = 100.0f;
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2021,Calendar.JULY,22,16,0);
        Calendar cal2 = Calendar.getInstance();
        cal2.set(2021,Calendar.JULY,22,17,0);
        Ticket ticket = new Ticket("87", cal1, cal2);

        assertEquals(100.0f, Ticketmachine.useTicket(ticket));
    }
}
