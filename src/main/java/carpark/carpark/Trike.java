package carpark.carpark;

class Trike extends Car {
    /**
     * Uses {@link Car#Car(int, String, String, String, Ticket, float, float) Car-Construktor} with pre-defined height, width and type-String
     */
    public Trike(int id, String color, String licensePlate, Ticket ticket) {
        super(id, color, "Trike", licensePlate, ticket , 1.88f, 1.06f);
    }
}
