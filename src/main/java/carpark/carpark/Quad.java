package carpark.carpark;

class Quad extends Car {
    /**
     * Uses {@link Car#Car(int, String, String, String, Ticket, float, float) Car-Construktor} with pre-defined height, width and type-String
     */
    public Quad(int id, String color, String licensePlate, Ticket ticket) {
        super(id, color, "Quad", licensePlate, ticket, 1.3f, 1.26f);
    }
}