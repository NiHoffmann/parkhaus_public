package carpark.carpark;

class Pickup extends Car {
    /**
     * Uses {@link Car#Car(int, String, String, String, Ticket, float, float) Car-Construktor} with pre-defined height, width and type-String
     */
    public Pickup(int id, String color, String licensePlate, Ticket ticket) {
        super(id, color, "Pickup", licensePlate, ticket,1.86f,1.81f );
    }
}