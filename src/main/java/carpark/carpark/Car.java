package carpark.carpark;
public abstract class Car implements CarInt {
    /** id Database ID */
    int id;
    /** color Color of the car (color code) */
    String color;
    /** vehicleType Type of vehicle (type-String) */
    String vehicleType;
    /** licensePlate License plate (String) */
    String licensePlate;
    /** isParked parking status (boolean) */
    boolean isParked = false;
    /** ticket associated with this car (Ticket-Object) */
    Ticket ticket;
    /** height Height of this Car (float) */
    float height;
    /** width Width of this Car (float) */
    float width;
    /**
     * Constructor Method
     * @param id Database ID
     * @param color Color of the car (color code)
     * @param vehicleType Type of vehicle (any)
     * @param licensePlate License plate
     */
    protected Car(int id, String color, String vehicleType, String licensePlate, Ticket ticket, float width, float height) {
        this.id = id;
        this.color = color;
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
        this.ticket = ticket;
        this.width = width;
        this.height = height;
    }
    /**
     * @param id Database ID
     * @param color Color of the car (color code)
     */
    public Car(int id, String color) {
        this.color = color;
        this.id = id;
    }
    /**
     * Getter
     * @return Width of the car (float)
     */
    public float getWidth() {
        return width;
    }
    /**
     * Getter
     * @return Height of the car (float)
     */
    public float getHeight(){
        return height;
    }
    /**
     * Getter
     * @return license of the car (String)
     */
    @Override
    public String license() {
        return null;
    }
    /**
     * Getter
     * @return Color of the car (color code)
     */
    @Override
    public String color() {
        return color;
    }
    /**
     * Getter
     * @return Ticket of the car (Ticket-Object)
     */
    @Override
    public Ticket ticket(){
        return ticket;
    }
    /**
     * Getter
     * @return Database ID
     */
    @Override
    public int getId() {
        return id;
    }
    /**
     * Getter
     * @return Licenseplate
     */
    @Override
    public String licensePlate() {
        return this.licensePlate;
    }
    /**
     * Getter
     * @return Vehicle type any
     */
    @Override
    public String vehicleType() {
        return this.vehicleType;
    }
    /**
     * Getter
     * @return If the car is parked or not
     */
    @Override
    public boolean isParked() {
        return this.isParked;
    }
}