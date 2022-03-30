package carpark.carpark;

class SUV extends Car {
    /**
     * Uses {@link Car#Car(int, String, String, String, Ticket, float, float) Car-Construktor} with pre-defined height, width and type-String
     */
    public SUV(int id, String color, String licensePlate, Ticket ticket) {
        super(id, color, "SUV", licensePlate, ticket, 1.85f, 1.77f);
    }
}