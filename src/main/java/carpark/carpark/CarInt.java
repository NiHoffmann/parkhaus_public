package carpark.carpark;
public interface CarInt {
    /**
     * Getter
     * @return Width of the car (float)
     */
     float getWidth();
    /**
     * Getter
     * @return Height of the car (float)
     */
     float getHeight();
    /**
     * Getter
     * @return license of the car (String)
     */
     String license();
    /**
     * Getter
     * @return Color of the car (color code)
     */
     String color();
    /**
     * Getter
     * @return Ticket of the car (Ticket-Object)
     */
     Ticket ticket();
    /**
     * Getter
     * @return Database ID
     */
     int getId();
    /**
     * Getter
     * @return Licenseplate
     */
     String licensePlate();
    /**
     * Getter
     * @return Vehicle type any
     */
     String vehicleType();
    /**
     * Getter
     * @return If the car is parked or not
     */
     boolean isParked();
}