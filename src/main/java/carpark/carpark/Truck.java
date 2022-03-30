package carpark.carpark;

class Truck extends Car {
    boolean trailer;
    /**
     * Uses {@link Car#Car(int, String, String, String, Ticket, float, float) Car-Construktor} with pre-defined height, width and type-String
     */
    public Truck(int id, String color, String licensePlate, Ticket ticket, boolean trailer) {
        super(id, color, "Truck", licensePlate, ticket, 4.0f , 2.55f);
        this.trailer = trailer;
    }

    /**
     * Getter
     * @return If the Truck has a trailer
     */
    public boolean hasTrailer(){
        return this.trailer;
    }
}
