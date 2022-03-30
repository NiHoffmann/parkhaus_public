package carpark.carpark;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

/** Controls the statists for the corresponding views */
public class StatisticsHandler {
    /**
     * request
     */
    HttpServletRequest request;
    /**
     * response
     */
    HttpServletResponse response;
    /**Constant for text/html*/
    public static final String TEXT_HTML = "text/html";

    /**
     * Regulates the request and response
     *
     * @param request  Request to the server
     * @param response Response from the server
     */
    public StatisticsHandler(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * Daily income from the parking garage
     * @param scanDate Today date
     * @return Daily income
     */
    public double dailyIncome(Calendar scanDate) throws IOException {
        double income = ServerData.dailyIncome(scanDate);

        if (response != null) {
            PrintWriter out = response.getWriter();
            response.setContentType(TEXT_HTML);
            out.println("Tageseinnahmen: &euro;" + income);
        }

        return income;
    }

    /**
     * Weekly income from the parking garage
     * @param scanDate Today date
     * @return Weekly income
     */
    public double weeklyIncome(Calendar scanDate) throws IOException {
        double income = ServerData.weeklyIncome(scanDate);

        if (response != null) {
            PrintWriter out = response.getWriter();
            response.setContentType(TEXT_HTML);
            out.println("Wocheneinnahmen: &euro;" + income);
        }

        return income;
    }

    /**
     * Calculates the current price
     * @param currentTime Total time the customer has been in the parking garage
     * @return Current price
     */
    public float customerView(long currentTime) throws IOException {

        long enterTime = ServerData.getLastEntered().getEntered();
        long currentStay = currentTime - enterTime;
        float currentPrice = currentStay / 1000f;

        if (response != null) {
            PrintWriter out = response.getWriter();
            response.setContentType(TEXT_HTML);
            out.println("Bisherige Kosten: &euro;" + currentPrice);
        }
        return currentPrice;
    }
}