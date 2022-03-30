package carpark.carpark;

import java.util.Calendar;
import java.util.regex.Pattern;

public class Body implements BodyInt {
    /**
     * Enum fields for different parts of Body used for debugging
     */
    public enum BodyField {
        TYPE,
        CAR_NUMBER,
        ENTERED,
        DURATION,
        PRICE,
        TICKET_ID,
        COLOR,
        PARKING_SPOT_NUMBER,
        VEHICLE_TYPE,
        LICENSE_PLATE,
        CORRECT
    }

    /** String generated upon vehicle entry into a parking spot */
    private final String body;

    /** enter or leave */
    private final String type;

    /** Number of the car */
    private final int carNr;

    /** Timestamp of entry */
    private final long entered;

    /** Length of stay */
    private long duration;

    /** Paid amount */
    private float price;

    /** ID of the Ticket */
    private final String ticketId;

    /** Vehicle color */
    private final String color;

    /** Number of the Parking Spot the Car was parked in */
    private final int parkingSpotNumber;

    /** Type of Client */
    private final String clientCategory;

    /** Type of Vehicle (Car, Bike, ...) */
    private final String vehicleType;

    /** License Plate of parked Vehicle */
    private final String licensePlate;

    /**Constant for enter*/
    private static final String ENTER = "enter";

    /**Constant for leave*/
    private static final String LEAVE = "leave";

    /**
     * Constructor of Body Class. Splits the Body String into variables
     * Also checks if the String is in an acceptable form, throws Exception otherwise
     * @param body The String generated upon vehicle entry into a parking spot
     */
    public Body(String body) {
        this.body = body;
        String[] bodyParts = body.split(",");

        this.type = bodyParts[0];
        this.carNr = Integer.parseInt(bodyParts[1]);
        this.entered = Long.parseLong(bodyParts[2]);
        if (this.type.equals(LEAVE)){
            this.duration = Long.parseLong(bodyParts[3]);
            this.price = Float.parseFloat(bodyParts[4]);
        }

        this.ticketId = bodyParts[5];
        this.color = bodyParts[6];
        this.parkingSpotNumber = Integer.parseInt(bodyParts[7]);
        this.clientCategory = bodyParts[8];
        this.vehicleType = bodyParts[9];
        this.licensePlate = bodyParts[10];
    }

    public String getBody() {
        return this.body;
    }

    public String getType() {
        return type;
    }

    public int getCarNr() {
        return carNr;
    }

    public long getEntered() {
        return entered;
    }

    public long getDuration() {
        return duration;
    }

    public float getPrice() {
        return price;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getColor() {
        return color;
    }

    public int getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public String getClientCategory() {
        return clientCategory;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * Format generated String into CSV readable String
     * @return The String in CSV Format
     */
    public String toApiFormat() {
        //enter/ oder leave/ haben beide länge 0->5 also ab 6 weggeschnitten

        return getBody().substring(6).replace(',', '/');
    }

    /**
     * Generates a Calendar object from the UNIX entered timestamp
     * @return Calendar object
     */
    public Calendar generateCalendar() {
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(this.entered);

        return date;
    }

    /**
     * Generates Vehicle of given type
     * @return Generated Vehicle
     */
    public Car generateCar() {
        switch (this.vehicleType) {
            case "Truck":
                return new Truck(this.carNr, this.color, this.licensePlate, new Ticket(ticketId), false);
            case "Pickup":
                return new Pickup(this.carNr, this.color, this.licensePlate, new Ticket(ticketId));
            case "SUV":
                return new SUV(this.carNr, this.color, this.licensePlate, new Ticket(ticketId));
            case "Zweirad":
                return new Zweirad(this.carNr, this.color, this.licensePlate, new Ticket(ticketId));
            case "Trike":
                return new Trike(this.carNr, this.color, this.licensePlate, new Ticket(ticketId));
            case "Quad":
                return new Quad(this.carNr, this.color, this.licensePlate, new Ticket(ticketId));
            default:
                return null;
        }
    }

    /**
     * Generates a new Ticket
     * @return New Ticket
     */
    public Ticket generateTicket() {
        Calendar entry = Calendar.getInstance();
        entry.setTimeInMillis(this.entered * 1000);

        return Ticketmachine.generateTicket(this.ticketId, entry);
    }

    public static BodyField checkBody(String[] bodyParts) {
        if (!checkType(bodyParts[0]))
            return BodyField.TYPE;
        if (!checkCarNumber(bodyParts[1]))
            return BodyField.CAR_NUMBER;
        if (!checkEntered(bodyParts[2]))
            return BodyField.ENTERED;

        if (bodyParts[0].equals(LEAVE)) {
            if (!checkDuration(bodyParts[3]))
                return BodyField.DURATION;
            if (!checkPrice(bodyParts[4]))
                return BodyField.PRICE;
        }

        if (!checkTicketID(bodyParts[5]))
            return BodyField.TICKET_ID;
        if (!checkColor(bodyParts[6]))
            return BodyField.COLOR;
        if (!checkParkingSpotNumber(bodyParts[7]))
            return BodyField.PARKING_SPOT_NUMBER;
        if (!checkVehicleType(bodyParts[9]))
            return BodyField.VEHICLE_TYPE;
        if (!checkLicensePlate(bodyParts[10]))
            return BodyField.LICENSE_PLATE;

        return BodyField.CORRECT;
    }

    public static boolean checkType(String string) {
        return string.equals(ENTER) || string.equals(LEAVE);
    }

    public static boolean checkCarNumber(String string) {
        return Pattern.compile("^([0-9]|[1-9][0-9])$").matcher(string).matches();
    }

    public static boolean checkEntered(String string) {
        return Pattern.compile("^[0-9]{13,20}$").matcher(string).matches();
    }

    public static boolean checkDuration(String string) {
        return Pattern.compile("^[0-9]{1,20}$|_").matcher(string).matches();
    }

    public static boolean checkPrice(String string) {
        return Pattern.compile("^[0-9]{1,20}$|_").matcher(string).matches();
    }

    public static boolean checkTicketID(String string) {
        return Pattern.compile("[a-fA-F0-9]{32}$").matcher(string).matches();
    }

    public static boolean checkColor(String string) {
        return Pattern.compile("#[a-fA-F0-9]{6}$").matcher(string).matches();
    }

    public static boolean checkParkingSpotNumber(String string) {
        return Pattern.compile("^([0-9]|[1-9][0-9])$").matcher(string).matches();
    }

    public static boolean checkVehicleType(String string) {
        return Pattern.compile("^(PKW|Pickup|SUV|Zweirad|Trike|Quad|Truck)$").matcher(string).matches();
    }

    public static boolean checkLicensePlate(String string) {
        return Pattern.compile("[A-Z]{1,2}-[A-Z]\\s[0-9]{1,3}$").matcher(string).matches();
    }
}
