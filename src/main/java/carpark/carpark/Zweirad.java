package carpark.carpark;

class Zweirad extends Car {
    /**
     * Uses {@link Car#Car(int, String, String, String, Ticket, float, float) Car-Construktor} with pre-defined height, width and type-String
     */
    public Zweirad(int id, String color, String licensePlate, Ticket ticket) {
        super(id, color, "Zweirad", licensePlate, ticket, 0.7f, 0.75f);
    }
}
