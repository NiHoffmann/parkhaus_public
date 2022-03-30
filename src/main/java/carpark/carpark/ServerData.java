package carpark.carpark;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/** Class for performing background tasks in context of UI and further calculations */
public class ServerData {
    /** Array of all received enter/leave strings */
    static List<Body> history = null;
    /** Latest vehicle to enter a parking spot */
    private static Body lastEntered = null;
    /** Latest vehicle to exit the car park */
    private static Body lastExited = null;
    /**Constant for enter*/
    public static final String ENTER = "enter";
    /**Constant for leave*/
    public static final String LEAVE = "leave";
    /**Private constructor of ServerData class*/
    private ServerData(){

    }
    /** All vehicles that are currently within the car park */
    public static long getCarsEntered() {
        return history.stream()
                .filter(body -> body.getType().equals(ENTER))
                .count();
    }
    /** Amount of vehicle that are currently within the car park */
    public static long getCarsLeft() {
        return history.stream()
                .filter(body -> body.getType().equals(LEAVE))
                .count();
    }
    /** Calculates the sum of all income */
    public static double getTotalIncome() {
        return history.stream()
                .filter(body -> body.getType().equals(LEAVE))
                .mapToDouble(Body::getPrice)
                .sum();
    }
    /** returns cars that have entered but never left, used to get when the server starts */
    public static List<Body> getUnexitedBodies() {
        return history.stream()
                .filter(body -> body.getType().equals(ENTER))
                .filter(body -> !hasExited(body))
                .collect(Collectors.toCollection(ArrayList<Body>::new));
    }
    /** checks if a body that entered has also exited */
    public static boolean hasExited(Body body) {
        for (Body compareBody: history) {
            if (compareBody.getType().equals(LEAVE) && body.getTicketId().equals(compareBody.getTicketId())) {
                return true;
            }
        }
        return false;
    }
    /**Return lingtimeparking counter**/
    public static long getLongTermParking() {
        return history.stream()
                .filter(body -> body.getType().equals(LEAVE))
                .filter(body -> body.getDuration() > 20000)
                .count();
    }
    /**Return shorttimeparking counter**/
    public static long getShortTermParking() {
        return history.stream()
                .filter(body -> body.getType().equals(LEAVE))
                .filter(body -> body.getDuration() <= 20000)
                .count();
    }
    /**Return the body of the car that entered last**/
    public static Body getLastEntered() {
        for (Body body: history) {
            if(body.getType().equals(ENTER)){
                lastEntered = body;
            }
        }

        return lastEntered;
    }
    /**Return the body of the car that exited last**/
    public static Body getLastExited() {
        for (Body body: history) {
            if(body.getType().equals(LEAVE)){
                lastExited = body;
            }
        }

        return lastExited;
    }
    /**
     * Income from the parking garage between the two calendars, exclusive
     * @param from Prior date
     * @param to After date
     * @return Income
     */
    public static double income(Calendar from, Calendar to) {
        return ServerData.history
                .stream()
                .filter(body -> {
                    Calendar date = body.generateCalendar();
                    return date.after(from) && date.before(to);
                })
                .mapToDouble(Body::getPrice)
                .sum() / 100;
    }
    /**
     * Daily income from the parking garage
     * @param scanDate Today date
     * @return Daily income
     */
    public static double dailyIncome(Calendar scanDate) {
        Calendar dayBefore = Calendar.getInstance();
        dayBefore.setTimeInMillis(scanDate.getTimeInMillis() - 86400000); //86400 is a day in epoch seconds

        return income(dayBefore, scanDate);
    }
    /**
     * Weekly income from the parking garage
     * @param scanDate Today date
     * @return Weekly income
     */
    public static double weeklyIncome(Calendar scanDate) {
        Calendar weekBefore = Calendar.getInstance();
        weekBefore.setTimeInMillis(scanDate.getTimeInMillis() - (86400000 * 7));
        return income(weekBefore, scanDate);
    }
    /**set last entered body**/
    public static void setLastEntered(Body body) { lastEntered = body; }
    /**set last exited body**/
    public static void setLastExited(Body body) { lastExited = body; }
    /**set histroy (body arraylist)**/
    protected static void setHistory(List<Body> history) {
        ServerData.history = history;
    }
    /** Creates a backup of the history array */
    public static void backup() {
        CSV.save("History", history.stream().map(Body::getBody).toArray(String[]::new));
    }
    /** Performed when a vehicle enters the car park */
    public static void addEvent(Body body) {
        history.add(body);
    }
    /** Calculates the average income per vehicle */
    public static double calculateAverageIncome(){ return getTotalIncome() / getCarsLeft(); }

}
