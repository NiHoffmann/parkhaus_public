package carpark.carpark;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
class CarTest {
    int id = 1;
    String color = "#FFFF00";
    String plate = "DB AS 112";
    Ticket ticket = new Ticket("asdasdyxvyxcasdasd1");
    Car p;
    @AfterEach
    void attributeTest() {
        assertEquals(p.color, color);
        assertEquals(p.id , id);
        assertEquals(p.ticket, ticket);
        assertEquals(p.licensePlate, plate);
    }
    @Test
    void pickupTest() {
        p = new Pickup(id, color , plate , ticket);
        assertEquals(1.86f, p.getWidth());
        assertEquals(1.81f, p.getHeight());
        assertEquals("Pickup", p.vehicleType);
    }
    @Test
    void quadTest() {
        p = new Quad(id, color , plate , ticket);
        assertEquals(1.3f, p.getWidth());
        assertEquals(1.26f, p.getHeight());
        assertEquals("Quad", p.vehicleType);
    }
    @Test
    void suvTest() {
        p = new SUV(id, color , plate , ticket);
        assertEquals(1.77f, p.getHeight());
        assertEquals(1.85f, p.getWidth());
        assertEquals("SUV", p.vehicleType);
    }
    @Test
    void trikeTest() {
        p = new Trike(id, color , plate , ticket);
        assertEquals(1.06f, p.getHeight());
        assertEquals(1.88f, p.getWidth());
        assertEquals("Trike", p.vehicleType);
    }
    @Test
    void truckTest() {
        p = new Truck(id, color , plate , ticket, true);
        assertEquals(2.55f, p.getHeight());
        assertEquals(4.0f, p.getWidth());
        assertEquals("Truck", p.vehicleType);
        Assertions.assertTrue(((Truck) p).hasTrailer());
    }
    @Test
    void zweiradTest() {
        p = new Zweirad(id, color , plate , ticket);
        assertEquals(0.75f, p.getHeight());
        assertEquals(0.7f, p.getWidth());
        assertEquals("Zweirad", p.vehicleType);
    }
}