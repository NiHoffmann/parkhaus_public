package carpark.carpark;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Class that writes data into the User Interface */
class UiWriter {

    /**
     * request
     */
    HttpServletRequest request;
    /**
     * response
     */
    HttpServletResponse response;
    /**
     * Printer which puts the text on the page
     */
    PrintWriter out;
    /**
     * Set contentType to text/html
     */
    public static final String TEXT_HTML = "text/html";
    /**
     * Set contentType to text/plain
     */
    public static final String TEXT_PLAIN = "text/plain";
    /**Logger for Errors*/
    private static final Logger LOGGER = Logger.getLogger(UiWriter.class.getName());

    /**
     * Regulates the request and response
     *
     * @param request  Request to the server
     * @param response Response from the server
     */
    public UiWriter(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.request = request;
        this.response = response;

        this.out = response.getWriter();
    }

    /**
     * Calculates and displays the total revenue of the parking garage
     */
    public void sum() {
        double sum = ServerData.getTotalIncome() / 100;

        response.setContentType(TEXT_HTML);
        out.println("Gesamteinnahmen: &euro; " + sum);
        LOGGER.log(Level.FINEST, String.format("%s = ", "sum"), sum);
    }

    /**
     * Calculates and displays the total average of the parking garage
     */
    public void avg() {
        double avg = ServerData.calculateAverageIncome() / 100;

        response.setContentType(TEXT_HTML);
        out.println("durschnittliche Kosten: &euro; " + avg + " durchschnittliche Dauer: " + avg + " Minuten");
        LOGGER.log(Level.FINEST, String.format("%s = ", "avg"), avg);
    }

    /**
     * Displays the total number of visitors of the parking garage
     */
    public void besucher() {
        long besucher = ServerData.getCarsEntered();

        response.setContentType(TEXT_HTML);
        out.println("Gesamt Besucherzahl " + besucher);
        LOGGER.log(Level.FINEST, String.format("%s %s ", "Gesamt", "Besucherzahl"), besucher);
    }

    /**
     * Calculates and displays the price per hour
     */
    public void preis() {
        Body lastExited = ServerData.getLastExited();

        response.setContentType(TEXT_HTML);

        if (lastExited == null) {
            out.println("Preis pro Stunde: &euro;" + 100);
            LOGGER.log(Level.FINEST, String.format("%s pro Stunde = ", "Preis"), 100);
            return;
        }

        float preis = (lastExited.getDuration() / lastExited.getPrice()) * 10;
        out.println("Preis pro Stunde: &euro;" + preis);
        LOGGER.log(Level.FINEST, String.format("%s pro Stunde = ", "Preis"), preis);
    }

    /** Draws a bar chart which displays the amount of customers currently in the car park and the amount of customers in total */
    public void graphAnzahl() {
        response.setContentType(TEXT_PLAIN);

        JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
                .add("data", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("y", Json.createArrayBuilder()
                                        .add(Carpark.getSpotsOccupied())
                                        .add(ServerData.getLongTermParking() + ServerData.getShortTermParking() + Carpark.getSpotsOccupied()))

                                .add("x", Json.createArrayBuilder()
                                        .add("Anzahl")
                                        .add("Anzahl Gesamt"))
                                .add("type", "bar")));

        JsonObject jo = objectBuilder.build();
        out.println(jo);

    }

    /** Draws a pie chart which shows the amount of long- and short-term-customers */
    public void graphParkverhalten() {
        response.setContentType(TEXT_PLAIN);

        JsonObjectBuilder objectBuilder = Json.createObjectBuilder()

                .add("data", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("values", Json.createArrayBuilder()
                                        .add(ServerData.getLongTermParking())
                                        .add(ServerData.getShortTermParking()))
                                .add("labels", Json.createArrayBuilder()
                                        .add("Langzeitparker (ab 20 Min)")
                                        .add("Kurzzeitparker"))

                                .add("type", "pie")));

        objectBuilder .add("layout", Json.createObjectBuilder()
                .add("height", 400)
                .add("width", 500));


        JsonObject jo = objectBuilder.build();
        out.println(jo);
    }
}