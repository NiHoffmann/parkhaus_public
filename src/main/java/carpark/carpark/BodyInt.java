package carpark.carpark;

public interface BodyInt {
    /** @return String generated upon vehicle entry into a parking spot */
    public String getBody();

    /** @return enter or leave */
    public String getType();

    /** @return Number of the car */
    public int getCarNr();

    /** @return Timestamp of entry */
    public long getEntered();

    /** @return Length of stay */
    public long getDuration();

    /** @return Paid amount */
    public float getPrice();

    /** @return ID of the Ticket */
    public String getTicketId();

    /** @return Vehicle color */
    public String getColor();

    /** @return Number of the Parking Spot the Car was parked in */
    public int getParkingSpotNumber();

    /** @return Type of Client */
    public String getClientCategory();

    /** @return Type of Vehicle (Car, Bike, ...) */
    public String getVehicleType();

    /** @return License Plate of parked Vehicle */
    public String getLicensePlate();

    /**
     * Generates Vehicle of given type
     * @return Generated Vehicle
     */
    public Car generateCar();

    /**
     * Generates a new Ticket
     * @return New Ticket
     */
    public Ticket generateTicket();
}
